package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen

import android.content.Context
import com.teamunemployment.lolanalytics.Utils.producePieChartData
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.HeadToHeadStat

/**
 * @author Josiah Kendall
 */
class DetailsPresenter(private val detailsInteractor: DetailsInteractor,
                       private val context : Context) {

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
    fun start(matchId : Long, summonerId : Long, role : String) {
        detailsInteractor.fetchDetailsForMatch(matchId, summonerId, role, this)
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
        view.setKillsChart(result.kda[EARLY_GAME].producePieChartData(context))
        view.setDeathsChart(result.kda[MID_GAME].producePieChartData(context))
        view.setAssistsChart(result.kda[LATE_GAME].producePieChartData(context))
        view.setCreepsEarlyGameChart(result.creeps[EARLY_GAME].producePieChartData(context))
        view.setCreepsMidGameChart(result.creeps[MID_GAME].producePieChartData(context))
        view.setCreepsLateGameChart(result.creeps[LATE_GAME].producePieChartData(context))
        view.setDamageDealtEarlyGameChart(result.damageDealt[EARLY_GAME].producePieChartData(context))
        view.setDamageDealtMidGameChart(result.damageDealt[MID_GAME].producePieChartData(context))
        view.setDamageDealtLateGameChart(result.damageDealt[LATE_GAME].producePieChartData(context))
        view.setDamagetakenEarlyGameChart(result.damagetaken[EARLY_GAME].producePieChartData(context))
        view.setDamagetakenMidGameChart(result.damagetaken[MID_GAME].producePieChartData(context))
        view.setDamagetakenLateGameChart(result.damagetaken[LATE_GAME].producePieChartData(context))
        view.setGoldEarlyGameChart(result.gold[EARLY_GAME].producePieChartData(context))
        view.setGoldMidGameChart(result.gold[MID_GAME].producePieChartData(context))
        view.setGoldLateGameChart(result.gold[LATE_GAME].producePieChartData(context))
        view.setXpEarlyGameChart(result.xp[EARLY_GAME].producePieChartData(context))
        view.setXpMidGameChart(result.xp[MID_GAME].producePieChartData(context))
        view.setXpLateGameChart(result.xp[LATE_GAME].producePieChartData(context))
    }


}