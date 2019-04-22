package com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.DetailsScreen

import android.util.Log
import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.Utils.*
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.data.model.SummonerRapidAccessObject
import com.teamunemployment.lolanalytics.data.room.MatchHistory.MatchHistoryDao
import com.teamunemployment.lolanalytics.data.room.champ.ChampDao
import com.teamunemployment.lolanalytics.data.room.champ.SimpleChampDao
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.MatchHistoryService
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import com.teamunemployment.lolanalytics.mock.MockHttpResponseInterceptor
import com.teamunemployment.lolanalytics.mock.MockMatchHistoryServiceResponses
import retrofit2.Call
import retrofit2.Response

/**
 * @author Josiah Kendall
 */
class DetailsInteractor(private val retrofitFactory: RetrofitFactory,
                        private val network: Network,
                        private val summonerRapidAccessObject: SummonerRapidAccessObject,
                        private val matchHistoryDao: MatchHistoryDao,
                        private val champDao: ChampDao,
                        private val simpleChampDao: SimpleChampDao) {

    private val mockResponses = MockMatchHistoryServiceResponses()
    private lateinit var presenter : DetailsPresenter

    private val mockInterceptor = MockHttpResponseInterceptor("200", 200)


    fun setPresenter(presenter: DetailsPresenter) {
        this.presenter = presenter
    }

    /**
     * Fetch the all the data that we need for the details page.
     *
     * @param matchId       The id of the match that we are fetching details for.
     * @param summonerId    The hero summonerId.
     * @param role          The role of the hero and his opponent
     * @param presenter     Callback to this when the data is loaded.
     */
    fun fetchDetailsForMatch(detailsUrl : String, presenter: DetailsPresenter) {
        async {
            val url = network.getUrlForRegion(summonerRapidAccessObject.getRegion())
            val matchHistoryService = retrofitFactory.produceRetrofitInterface(
                    MatchHistoryService::class.java, url)

            val call : Call<Result<MatchPerformanceDetails>> =
                    matchHistoryService.fetchMatchDetails("match/v1/loadMatchDetails/$detailsUrl")

            val response: Response<Result<MatchPerformanceDetails>> = await { call.execute() }

            val result: Result<MatchPerformanceDetails> = response.getMatchPerformanceDetails()
            val code = result.resultCode
            val headToHeadPerformance = calculatePerformanceForUser(result.data)
            presenter.handleDetailsResponse(code, result.data, headToHeadPerformance)
        }.onError {
            presenter.showMessage("Offline - check you internet connection")
        }
    }

//    fun fetchChamps()

    /**
     * Calculate how well a user performed based on the the given weighting of each stat. The user
     * can customise the stats to have whatever weighting they so desire.
     *
     * The total weighting of all stats is one. Each stat is multiplied by its weighting value.
     *
     * Default stats are :
     *
     *  - Kills (4)
     *  - Deaths (-6)
     *  - Assists (2)
     *  - creepsEarlyGame (2)
     *  - creepsMidGame (1)
     *  - creepsLateGame (0.05)
     *  - damageEarlyGame (0.05)
     *  - damageMidGame (0.05)
     *  - damageLateGame (0.05)
     *  - xpEarlyGame (0.05)
     *  - xpMidGame (0.00)
     *  - xpLateGame (0.00)
     *  - goldEarlyGame (0.05)
     *  - goldMidGame (0.025)
     *  - goldLateGame (0.025)
     *
     *  Because some of these stats are such different numbers, we cannot just multiply the score by
     *  the scale. (e.g 20k damage vs 4 kills...) So instead, we have to calculate the difference
     *  between the hero score and the villain score as a percentage, and multiply that.
     *  Think of these as percentages - when you increase one, the others drop uniformly.
     */
    fun calculatePerformanceForUser(performanceDetails: MatchPerformanceDetails) : HeadToHeadStat{
        // creep scores
        val earlyGameCreepScoreDiff = performanceDetails.creeps.earlyGame().heroStatValue
                .calculatePercentageDifference(performanceDetails.creeps.earlyGame().enemyStatValue)

        val midGameCreepScoreDiff = performanceDetails.creeps.midGame().heroStatValue
                .calculatePercentageDifference(performanceDetails.creeps.midGame().enemyStatValue)

        val lateGameCreepScoreDiff = performanceDetails.creeps.lateGame().heroStatValue
                .calculatePercentageDifference(performanceDetails.creeps.lateGame().enemyStatValue)

        val killsDifference = performanceDetails.kda.earlyGame().heroStatValue
                .calculatePercentageDifference(performanceDetails.kda.earlyGame().enemyStatValue)

        val deathsDifference = performanceDetails.kda.midGame().heroStatValue
                .calculatePercentageDifference(performanceDetails.kda.midGame().enemyStatValue)

        val assistsDifference = performanceDetails.kda.lateGame().heroStatValue
                .calculatePercentageDifference(performanceDetails.kda.lateGame().enemyStatValue)

        val earlyGameDamgeScoreDiff = performanceDetails.damageDealt.earlyGame().heroStatValue
                .calculatePercentageDifference(performanceDetails.damageDealt.earlyGame().enemyStatValue)

        val midGameDamgeScoreDiff = performanceDetails.damageDealt.midGame().heroStatValue
                .calculatePercentageDifference(performanceDetails.damageDealt.midGame().enemyStatValue)

        val lateGameDamgeScoreDiff = performanceDetails.damageDealt.lateGame().heroStatValue
                .calculatePercentageDifference(performanceDetails.damageDealt.lateGame().enemyStatValue)

        val earlyGameXpScoreDiff = performanceDetails.xp.earlyGame().heroStatValue
                .calculatePercentageDifference(performanceDetails.xp.earlyGame().enemyStatValue)

        val midGameXpScoreDiff = performanceDetails.xp.midGame().heroStatValue
                .calculatePercentageDifference(performanceDetails.xp.midGame().enemyStatValue)

        val lateGameXpScoreDiff = performanceDetails.xp.lateGame().heroStatValue
                .calculatePercentageDifference(performanceDetails.xp.lateGame().enemyStatValue)

        var totalScoreDifference = 0f
        totalScoreDifference = safeAdd(totalScoreDifference,killsDifference * 4)
        totalScoreDifference = safeAdd (totalScoreDifference,deathsDifference* -6)
        totalScoreDifference = safeAdd (totalScoreDifference,assistsDifference* 2)
        totalScoreDifference = safeAdd(totalScoreDifference,earlyGameCreepScoreDiff * 3)
        totalScoreDifference = safeAdd (totalScoreDifference, midGameCreepScoreDiff * 2)
        totalScoreDifference = safeAdd(totalScoreDifference, lateGameCreepScoreDiff* 1)
        totalScoreDifference = safeAdd(totalScoreDifference, earlyGameDamgeScoreDiff* 1)
        totalScoreDifference = safeAdd(totalScoreDifference,midGameDamgeScoreDiff* 2)
        totalScoreDifference = safeAdd(totalScoreDifference, lateGameDamgeScoreDiff* 3)
        totalScoreDifference = safeAdd(totalScoreDifference, earlyGameXpScoreDiff * 1)
        totalScoreDifference = safeAdd(totalScoreDifference, midGameXpScoreDiff * 1)
        totalScoreDifference = safeAdd(totalScoreDifference, lateGameXpScoreDiff* 1)

        val heroTotalScore = 50 + (totalScoreDifference / 2)
        val enemyTotalScore = 50 - (totalScoreDifference / 2)

        return HeadToHeadStat(enemyTotalScore, heroTotalScore)
    }

    private fun safeAdd(og: Float,  adding : Float) : Float {
        if (!adding.isNaN()) {
            return og + adding
        }

        return og
    }

    fun fetchChampInfo(matchId: String, summonerId: String) {
        async {
           val matchHistoryObject =  await { matchHistoryDao.loadMatchHistoryCardData(matchId.toLong(), summonerId) }
            if(matchHistoryObject != null) {
                val champHero = await{champDao.loadChamp(matchHistoryObject.champId.toString(), summonerId)}
                val champEnemy = await{simpleChampDao.loadChamp(matchHistoryObject.enemyChampId.toString())}
                if (champHero != null) {
                    presenter.setHeroChamp(champHero.title)
                }
                Log.d("TEST", "Enemy champ is ${matchHistoryObject.enemyChampId}")
                Log.d("TEST", "Enemy champ is null :  ${champEnemy == null}")
                if (champEnemy != null) {
                    presenter.setEnemyChamp(champEnemy.title)
                }
            }
        }.onError {
            presenter.showMessage("Offline - check you internet connection")
        }
    }
}

