package com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab

import android.util.Log
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet

import com.teamunemployment.lolanalytics.data.model.MatchSummary
import com.teamunemployment.lolanalytics.data.model.SummonerRapidAccessObject
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.MatchHistoryCardViewContract
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.MatchHistoryCardData
import com.teamunemployment.lolanalytics.front_page.tabs.TabContract

import java.util.ArrayList

import com.github.mikephil.charting.data.PieEntry
import com.teamunemployment.lolanalytics.Utils.Role
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.MatchIdentifier
import timber.log.Timber


/**
 * @author Josiah Kendall.
 */
class MatchHistoryPresenter
constructor(private val matchHistoryInteractor: MatchHistoryInteractor,
            private val summonerRapidAccessDataObject : SummonerRapidAccessObject,
            private val roleUtils : Role,
            private val matchHistoryRecycleViewAdapter : MatchHistoryRecycleViewAdapter) : MatchHistoryTabContract.Presenter {
    override fun showMessage(message: String) {
        view.showMessage(message)
    }

    private lateinit var view: TabContract.View

    override fun start() {
        matchHistoryRecycleViewAdapter.matchHistoryPresenter = this
        matchHistoryRecycleViewAdapter.clearData()
        view.setAdapter(matchHistoryRecycleViewAdapter)

        if (summonerRapidAccessDataObject.champKey == -1) {
            Timber.d("Loading with no champ and role : ${summonerRapidAccessDataObject.role}")
            loadDataForRole(summonerRapidAccessDataObject.role, summonerRapidAccessDataObject.summonerId)
        } else {
            Timber.d("Loading with champ: ${summonerRapidAccessDataObject.champKey} and role : ${summonerRapidAccessDataObject.role}")
            loadDataForRole(
                    summonerRapidAccessDataObject.role,
                    summonerRapidAccessDataObject.summonerId,
                    summonerRapidAccessDataObject.champKey)
        }
    }

    override fun handleError(e: Throwable) {
        Log.d("MatchHistory", "Error: " + e.message)
    }

    override fun restart() {

    }

    override fun resume() {

    }

    override fun pause() {

    }

    override fun stop() {

    }

    override fun destroy() {

    }

    override fun loadDataForRole(role: Int, summonerId : String) {

        matchHistoryInteractor.loadMatches("oce",summonerId,role,matchHistoryRecycleViewAdapter.itemCount/*default*/,this)
//        matchHistoryInteractor.loadCachedMatchHistoryData(role, -1, this)
//        matchHistoryInteractor.loadFreshMatchHistoryData(role, -1, this)
    }

    fun loadDataForRole(role : Int, summonerId : String, champKey : Int) {
        matchHistoryInteractor.loadMatches(
                "oce",
                summonerId,
                role,
                champKey,
                matchHistoryRecycleViewAdapter.itemCount/*default*/,
                this)
    }

    override fun loadMatchSummary(matchId: Long) {

//        matchHistoryInteractor.LoadCachedMatchSummary(matchId, this)
//        matchHistoryInteractor.LoadFreshMatchSummary(matchId, this)
    }

    override fun setView(view: TabContract.View) {
        this.view = view
    }

    override fun onMatchSummaryLoadedSuccessfully(matchSummary: MatchSummary) {

    }

    override fun onError(e: Throwable) {
        //        Log.e("MatchHistory", "Error: " + e.getMessage());
        view.showMessage("An error occurred. Please try again.")
    }

    override fun onMatchListLoadedSuccessfully(result: ArrayList<MatchIdentifier>, code : Int) {
        if (code in 200..299) {
            matchHistoryRecycleViewAdapter.addData(result)
        } else {
            view.showMessage("Whoops, something went wrong :(")
        }
    }

    // todo remove this
    @Deprecated("use SummonerRapidAccessObject class now")
    override fun setRole(role: Int) {
//        this.role = role
    }

    /**
     * Load the cardUrl value.
     * @param id The match id for the value we want to load.
     * @param cardViewContract The cardUrl object to present the value back to.
     */
    override fun loadCardData(matchIdentifier : MatchIdentifier, cardViewContract : MatchHistoryCardViewContract, region : String) {
        // if we get -1 here we should probably remove this card
        val lane = roleUtils.produceCorrectRoleIntegerGivenRoleAndLane(matchIdentifier.role, matchIdentifier.lane)
        matchHistoryInteractor.loadMatchDetails(matchIdentifier.matchId, lane, this, cardViewContract, region,summonerRapidAccessDataObject.summonerId, matchIdentifier.timestamp )
    }

    fun loadMoreDataForRole(listSize : Int) {
        // Check if we have a whole number, so that we can know that we still have more data to fetch
        if((listSize.toFloat()/20) == (listSize / 20).toFloat()){
            if (summonerRapidAccessDataObject.champKey == -1) {
                matchHistoryInteractor.loadMatches(summonerRapidAccessDataObject.getRegion(),
                        summonerRapidAccessDataObject.summonerId,
                        summonerRapidAccessDataObject.role,
                        listSize,
                        this)
            } else {
                matchHistoryInteractor.loadMatches(summonerRapidAccessDataObject.getRegion(),
                        summonerRapidAccessDataObject.summonerId,
                        summonerRapidAccessDataObject.role,
                        summonerRapidAccessDataObject.champKey,
                        listSize,
                        this)
            }
        }
        // if list size is less than a multiple of 20, don't load anything

    }

    /**
     * Set our loaded cardUrl value back to the presenter for presenting to the cardUrl.
     * =====================
     * Note the cardViewContract being handed around. That is because we want to
     * maintain the same request/callback pattern that we use everywhere else for ease.
     * The reason we have to keep the instance is that we cannot reliably maintain a reference to that cardUrl
     * object any other way. The other option is to take the observables out of the interactor and process
     * them here in the presenter. I feel like this is the lesser of two evils.
     * @param matchHistoryCardData The cardUrl value to present.
     * @param cardViewContract The cardUrl view object to present to.
     */
    override fun setLoadedCardData(matchHistoryCardData: MatchHistoryCardData,
                                   cardViewContract: MatchHistoryCardViewContract,
                                   timestamp: Long,
                                   resultString: String) {
        val timestampString = matchHistoryInteractor.produceTimestamp(timestamp)
        cardViewContract.setTimeStamp(timestampString)
        cardViewContract.setHeroChampIcon("NOT_DONE" + matchHistoryCardData.champId + ".png")
        cardViewContract.setGraph1(matchHistoryCardData.earlyGame)
        cardViewContract.setGraph2(matchHistoryCardData.midGame)
        cardViewContract.setGraph3(matchHistoryCardData.lateGame)
        cardViewContract.setDetailsUrl(matchHistoryCardData.detailsUrl)
        cardViewContract.setResult(resultString)

        val enemyStat = matchHistoryCardData.earlyGame.enemyStatValue +
                matchHistoryCardData.midGame.enemyStatValue +
                matchHistoryCardData.lateGame.enemyStatValue
        val heroStat= matchHistoryCardData.earlyGame.heroStatValue +
                matchHistoryCardData.midGame.heroStatValue+
                matchHistoryCardData.lateGame.heroStatValue

        val summaryPerformance = HeadToHeadStat(enemyStat, heroStat)
        cardViewContract.setResult(resultString)
        cardViewContract.setSummaryChart(summaryPerformance)
    }

    fun fetchPieDataSet(HeadToHeadStat: HeadToHeadStat): PieDataSet {
        val entries = ArrayList<PieEntry>()

        entries.add(PieEntry(HeadToHeadStat.heroStatValue, "Hero"))
        entries.add(PieEntry(HeadToHeadStat.enemyStatValue, "Enemy"))

        return PieDataSet(entries, "")
    }

    fun fetchPieData(pieDataSet: PieDataSet): PieData{
        return PieData(pieDataSet!!)
    }

    fun onDetailsButtonClick(detailsUrl : String) {
        // launch new view with sliding animation
        view.launchDetailsActivity(detailsUrl)
    }
}
