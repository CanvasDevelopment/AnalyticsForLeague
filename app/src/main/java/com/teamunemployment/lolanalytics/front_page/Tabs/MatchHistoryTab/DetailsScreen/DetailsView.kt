package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.Snackbar.LENGTH_LONG
import android.support.v7.app.AppCompatActivity
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.Utils.Constant.Companion.EARLY_GAME
import com.teamunemployment.lolanalytics.Utils.Constant.Companion.LATE_GAME
import com.teamunemployment.lolanalytics.Utils.Constant.Companion.MID_GAME
import com.teamunemployment.lolanalytics.Utils.setDefaultStyle
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen.model.GameStageView
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Model.PieReadyComparisonResult
import kotlinx.android.synthetic.main.match_history_details_panel.*
import kotlinx.android.synthetic.main.match_history_details_view.*
import org.koin.android.ext.android.inject
import android.widget.Toast
import android.view.MotionEvent

/**
 * @author Josiah Kendall
 */
class DetailsView : AppCompatActivity() {

    val presenter by inject<DetailsPresenter>()
    private var x1: Float = 0.toFloat()
    private var x2: Float = 0.toFloat()
    private val MIN_DISTANCE = 150
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
        setClickHandlers()
        presenter.start(-1,-1, "top")
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> x1 = event.x
            MotionEvent.ACTION_UP -> {
                x2 = event.x
                val deltaX = x2 - x1
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    Toast.makeText(this, "left2right swipe", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onTouchEvent(event)
    }

    private fun setClickHandlers() {
        matchDetailsBackButton.setOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition( R.anim.slide_out_exit, R.anim.slide_in_exit)
    }


    /**
     * Show a message to the user in the form of a [Snackbar]
     *
     * @param message The message to display
     */
    fun showMessage(message : String) {
        Snackbar.make(base_root, message, LENGTH_LONG)
    }

    /**
     * Set the toolbar title
     */
    fun setToolbar(result : String, champName : String) {
        // todo figure out how to translate this
        matchHistoryDetailsToolbarTitle.text = """$result as $champName"""
    }

    fun setHeadToHeadPerformanceChart(headToHeadChartData: PieReadyComparisonResult) {
        performanceScore.data = headToHeadChartData.pieData
        performanceScore.setDefaultStyle(headToHeadChartData.centreText)
    }

    fun setKillsChart(killsChartDataSet: PieReadyComparisonResult) {
        kills.data = killsChartDataSet.pieData
        kills.setDefaultStyle(killsChartDataSet.centreText)
    }

    fun setDeathsChart(deathsData: PieReadyComparisonResult) {
        midGameTitle.data = deathsData.pieData
        midGameTitle.setDefaultStyle(deathsData.centreText)

    }

    fun setAssistsChart(assistsData : PieReadyComparisonResult) {
        assists.data = assistsData.pieData
        assists.setDefaultStyle(assistsData.centreText)
    }

    fun setCreepsEarlyGameChart(chartData: PieReadyComparisonResult, chartTitle : String) {
        earlyGameView.setCreeps(chartData.pieData, chartData.centreText,chartTitle)
    }

    fun setCreepsMidGameChart(chartData:PieReadyComparisonResult, chartTitle : String) {
        midGameView.setCreeps(chartData.pieData, chartData.centreText, chartTitle)
    }

    fun setCreepsLateGameChart(chartData: PieReadyComparisonResult, chartTitle : String) {
        lateGameView.setCreeps(chartData.pieData, chartData.centreText,chartTitle)
    }

    fun setDamageTakenEarlyGameChart(chartData: PieReadyComparisonResult, chartTitle : String) {
        earlyGameView.setDamageTaken(chartData.pieData, chartData.centreText,chartTitle)
    }

    fun setDamageDealtEarlyGameChart(chartData: PieReadyComparisonResult, chartTitle : String) {
        earlyGameView.setDamageDealt(chartData.pieData, chartData.centreText, chartTitle)
    }

    fun setGoldEarlyGameChart(chartData: PieReadyComparisonResult, chartTitle : String) {
        earlyGameView.setGold(chartData.pieData, chartData.centreText,chartTitle)
    }

    fun setXpEarlyGameChart(chartData: PieReadyComparisonResult, chartTitle : String) {
        earlyGameView.setXp(chartData.pieData, chartData.centreText, chartTitle)
    }

    fun setDamageTakenMidGameChart(chartData: PieReadyComparisonResult, chartTitle : String) {
        midGameView.setDamageTaken(chartData.pieData, chartData.centreText, chartTitle)
    }

    fun setDamageDealtMidGameChart(chartData: PieReadyComparisonResult, chartTitle : String) {
        midGameView.setDamageDealt(chartData.pieData, chartData.centreText, chartTitle)
    }

    fun setGoldMidGameChart(chartData: PieReadyComparisonResult, chartTitle : String) {
        midGameView.setGold(chartData.pieData, chartData.centreText, chartTitle)
    }

    fun setXpMidGameChart(chartData: PieReadyComparisonResult, chartTitle : String) {
        midGameView.setXp(chartData.pieData, chartData.centreText, chartTitle)
    }

    fun setDamageTakenLateGameChart(chartData: PieReadyComparisonResult, chartTitle : String) {
        lateGameView.setDamageTaken(chartData.pieData, chartData.centreText, chartTitle)
    }

    fun setDamageDealtLateGameChart(chartData: PieReadyComparisonResult, chartTitle : String) {
        lateGameView.setDamageDealt(chartData.pieData, chartData.centreText, chartTitle)
    }

    fun setGoldLateGameChart(chartData: PieReadyComparisonResult, chartTitle : String) {
        lateGameView.setGold(chartData.pieData, chartData.centreText, chartTitle)
    }

    fun setXpLateGameChart(chartData: PieReadyComparisonResult, chartTitle : String) {
        lateGameView.setXp(chartData.pieData, chartData.centreText, chartTitle)
    }
}