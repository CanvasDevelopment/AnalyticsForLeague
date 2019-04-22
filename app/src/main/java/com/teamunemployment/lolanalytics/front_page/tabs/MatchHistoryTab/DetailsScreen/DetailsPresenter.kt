package com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.DetailsScreen

import android.content.Context
import android.view.ViewGroup
import com.teamunemployment.lolanalytics.Utils.*
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.DetailsScreen.model.GameStageView

/**
 * @author Josiah Kendall
 */
class DetailsPresenter(private val detailsInteractor: DetailsInteractor,
                       private val context : Context,
                       private val viewProducer: ViewProducer,
                       private val network: Network) {

    private val EARLY_GAME = 0
    private val MID_GAME = 1
    private val LATE_GAME = 2

    private val detailsErrorMessages = DetailsErrorMessages()
    private lateinit var view : DetailsView

    fun setView(view : DetailsView) {
        this.view = view
    }

    /**
     * Trigger the loading of the data, which will be presented to the view once it is ready
     * @param matchId       The unique id of the match that we are fetching the data for.
     * @param summonerId    The unique Id of the summoner who is the hero in this match
     * @param role          The role of the summoner that we are interested in.
     */
    fun start(detailsUrl : String) {
        // process the image and get the ro
        val params = detailsUrl.split("/")
        val role = params[0]
        val matchId = params[1]
        val summonerId = params[2]
        detailsInteractor.setPresenter(this)
        detailsInteractor.fetchDetailsForMatch(detailsUrl, this)
        detailsInteractor.fetchChampInfo(matchId, summonerId)
    }

    /**
     * Handle the response from the server.
     *
     * @param code                  This is the response code from the server. If this is not a 200, we fetch the
     *                              appropriate error message and display it to the user.
     * @param result                This is the actual data. If the [code] is a 200, then we should have some juicy
     *                              data that we can display about the user and their performance in this match
     *                              through a variety of pie charts
     * @param headToHeadPerformance The calculated performance stat [HeadToHeadStat] for each the
     *                              hero and the villain
     */
    fun handleDetailsResponse(code : Int, result : MatchPerformanceDetails, headToHeadPerformance: HeadToHeadStat) {
        when(code) {
            200 -> handleHealthyResponse(result, headToHeadPerformance)
            404 -> view.showMessage(detailsErrorMessages.`404`())
            500 -> view.showMessage(detailsErrorMessages.`500`())
        }
    }

    /**
     * Handle a 200 response. This method pulls apart the data and applies the correct data to the
     * correct charts, using the extension method [producePieChartData] to create the pie chart data.
     * @param result The data that we need to display on screen.
     */
    private fun handleHealthyResponse(result: MatchPerformanceDetails, headToHeadPerformance: HeadToHeadStat) {

        // Produce all our charts for data. Note that the kills and deaths use the EARLY_GAME etc..
        // variables because we want to just use the same style, for ease of programming.
        view.setHeadToHeadPerformanceChart(headToHeadPerformance.producePieChartData(context))
        view.setKillsChart(result.kda.earlyGame().producePieChartData(context))
        view.setDeathsChart(result.kda.midGame().producePieChartData(context))
        view.setAssistsChart(result.kda.lateGame().producePieChartData(context))
        view.setCreepsEarlyGameChart(result.creeps[EARLY_GAME].producePieChartData(context), "Creeps")
        view.setCreepsMidGameChart(result.creeps[MID_GAME].producePieChartData(context), "Creeps")
        view.setCreepsLateGameChart(result.creeps[LATE_GAME].producePieChartData(context), "Creeps")
        view.setDamageDealtEarlyGameChart(result.damageDealt[EARLY_GAME].producePieChartData(context), "Damage Dealt")
        view.setDamageDealtMidGameChart(result.damageDealt[MID_GAME].producePieChartData(context), "Damage Dealt")
        view.setDamageDealtLateGameChart(result.damageDealt[LATE_GAME].producePieChartData(context), "Damage Dealt")
        view.setGoldEarlyGameChart(result.gold[EARLY_GAME].producePieChartData(context), "Gold Earned")
        view.setGoldMidGameChart(result.gold[MID_GAME].producePieChartData(context), "Gold Earned")
        view.setGoldLateGameChart(result.gold[LATE_GAME].producePieChartData(context), "Gold Earned")
        view.setXpEarlyGameChart(result.xp[EARLY_GAME].producePieChartData(context), "XP Earned")
        view.setXpMidGameChart(result.xp[MID_GAME].producePieChartData(context), "XP Earned")
        view.setXpLateGameChart(result.xp[LATE_GAME].producePieChartData(context), "XP Earned")
    }

    /**
     * Create the game stage view that we want. This returns a gameStageView object that we can use to
     * set data to the actual view.
     * @param gameStage An int reflecting the game stage that we want. Must be [EARLY_GAME], [MID_GAME]
     *                  or [LATE_GAME].
     * @param parent    The parent that we want to add this view too.
     */
    fun produceGameStageView(gameStage : Int, parent : ViewGroup): GameStageView {
        val gameStageView = viewProducer.produceGameStageView(parent)

        when(gameStage) {
            EARLY_GAME -> gameStageView.setViewTitle("Early Game")
            MID_GAME-> gameStageView.setViewTitle("Mid Game")
            LATE_GAME-> gameStageView.setViewTitle("Late Game")
        }

        return gameStageView
    }

//    fun setChamps(champId: Long, enemyChampId: Long) {
//        network.getRawUrl()
//        view.setHeroChamp(champId)
//        view.setEnemyChampId(enemyChampId)
//    }

    fun setHeroChamp(title: String) {

        val champString = title
                .replace(" ", "")
                .replace("'", "")
                .replace(".", "")

        view.setHeroChamp("${network.getRawUrl("")}/champion/$champString.png")
    }

    fun setEnemyChamp(title : String) {
        val champString = title
                .replace(" ", "")
                .replace("'", "")
                .replace(".", "")
        view.setEnemyChamp("${network.getRawUrl("")}/champion/$champString.png")
    }

    fun showMessage(message : String) {
        view.showMessage(message)
    }

}