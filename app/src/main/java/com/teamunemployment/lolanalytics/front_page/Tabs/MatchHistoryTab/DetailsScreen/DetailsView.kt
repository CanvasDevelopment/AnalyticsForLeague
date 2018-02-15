package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.mikephil.charting.data.ChartData
import com.github.mikephil.charting.data.PieData
import com.teamunemployment.lolanalytics.R

/**
 * @author Josiah Kendall
 */
class DetailsView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.match_history_details_view)
    }

    /**
     * Show a message to the user in the form of a snackbar
     *
     * @param message The message to display
     */
    fun showMessage(message : String) {

    }

    fun setHeadToHeadPerformanceChart(headToHeadChartData: PieData) {

    }
    fun setKillsChart(killsChartDataSet: PieData) {

    }

    fun setDeathsChart(produceBarChart: PieData) {

    }

    fun setAssistsChart(produceBarChart: PieData) {

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