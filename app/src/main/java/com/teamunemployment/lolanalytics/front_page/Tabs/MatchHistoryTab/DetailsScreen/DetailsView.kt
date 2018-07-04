package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.Snackbar.LENGTH_LONG
import android.support.v7.app.AppCompatActivity
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
        presenter.start(1, 1, "top")
        earlyGameView = presenter.produceGameStageView(EARLY_GAME, earlyGameHolder)
        midGameView = presenter.produceGameStageView(MID_GAME, midGameHolder)
        lateGameView = presenter.produceGameStageView(LATE_GAME, lateGameHolder)
    }

    /**
     * Show a message to the user in the form of a [Snackbar]
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
        earlyGameView.setCreeps(chartData)
    }

    fun setCreepsMidGameChart(chartData: PieData) {
        midGameView.setCreeps(chartData)
    }

    fun setCreepsLateGameChart(chartData: PieData) {
        lateGameView.setCreeps(chartData)
    }

    fun setDamageTakenEarlyGameChart(chartData: PieData) {
        earlyGameView.setDamageTaken(chartData)
    }

    fun setDamageDealtEarlyGameChart(chartData: PieData) {
        earlyGameView.setDamageDealt(chartData)

    }

    fun setGoldEarlyGameChart(chartData: PieData) {
        earlyGameView.setGold(chartData)
    }

    fun setXpEarlyGameChart(chartData: PieData) {
        earlyGameView.setXp(chartData)
    }

    fun setDamageTakenMidGameChart(chartData: PieData) {
        midGameView.setDamageTaken(chartData)
    }

    fun setDamageDealtMidGameChart(chartData: PieData) {
        midGameView.setDamageDealt(chartData)
    }

    fun setGoldMidGameChart(chartData: PieData) {
        midGameView.setGold(chartData)
    }

    fun setXpMidGameChart(chartData: PieData) {
        midGameView.setXp(chartData)
    }

    fun setDamageTakenLateGameChart(chartData: PieData) {
        lateGameView.setDamageTaken(chartData)
    }

    fun setDamageDealtLateGameChart(chartData: PieData) {
        lateGameView.setDamageDealt(chartData)
    }

    fun setGoldLateGameChart(chartData: PieData) {
        lateGameView.setGold(chartData)
    }

    fun setXpLateGameChart(chartData: PieData) {
        lateGameView.setXp(chartData)
    }
}