package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.Snackbar.LENGTH_LONG
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.mikephil.charting.data.PieData
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.Utils.Constant.*
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen.model.GameStageView
import kotlinx.android.synthetic.main.match_history_details_panel.*
import kotlinx.android.synthetic.main.match_history_details_view.*
import org.koin.android.ext.android.inject

/**
 * @author Josiah Kendall
 */
class DetailsView : AppCompatActivity() {

    val presenter by inject<DetailsPresenter>()

    private lateinit var earlyGameView : GameStageView
    private lateinit var midGameView : GameStageView
    private lateinit var lateGameView : GameStageView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.match_history_details_view)
        presenter.setView(this)
        earlyGameView = presenter.produceGameStageView(EARLY_GAME, contentHolder)
        midGameView = presenter.produceGameStageView(MID_GAME, contentHolder)
        lateGameView = presenter.produceGameStageView(LATE_GAME, contentHolder)
    }

    /**
     * Show a message to the user in the form of a snackbar
     *
     * @param message The message to display
     */
    fun showMessage(message : String) {
        Snackbar.make(root, message, LENGTH_LONG)
    }

    fun setHeadToHeadPerformanceChart(headToHeadChartData: PieData) {
        performanceScore.data = headToHeadChartData
    }

    fun setKillsChart(killsChartDataSet: PieData) {
        kills.data = killsChartDataSet
    }

    fun setDeathsChart(deathsData: PieData) {
        deaths.data = deathsData
    }

    fun setAssistsChart(assistsData: PieData) {
        assists.data = assistsData
    }

    fun setCreepsEarlyGameChart(chartData: PieData) {

    }

    fun setCreepsMidGameChart(chartData: PieData) {

    }

    fun setCreepsLateGameChart(chartData: PieData) {

    }

    fun setDamagetakenEarlyGameChart(chartData: PieData) {

    }

    fun setDamageDealtEarlyGameChart(chartData: PieData) {

    }

    fun setGoldEarlyGameChart(chartData: PieData) {

    }

    fun setXpEarlyGameChart(chartData: PieData) {

    }

    fun setDamagetakenMidGameChart(chartData: PieData) {

    }

    fun setDamageDealtMidGameChart(chartData: PieData) {

    }

    fun setGoldMidGameChart(chartData: PieData) {

    }

    fun setXpMidGameChart(chartData: PieData) {

    }

    fun setDamagetakenLateGameChart(chartData: PieData) {

    }

    fun setDamageDealtLateGameChart(chartData: PieData) {

    }

    fun setGoldLateGameChart(chartData: PieData) {

    }

    fun setXpLateGameChart(chartData: PieData) {

    }

}