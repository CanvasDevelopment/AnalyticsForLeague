package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.DetailsScreen

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.Utils.*
import com.teamunemployment.lolanalytics.Utils.extensions.setGraphs
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.HeadToHeadStat
import kotlinx.android.synthetic.main.match_history_details_view.*
import kotlinx.android.synthetic.main.stat_details_view.*
import org.koin.android.ext.android.inject

/**
 * Created by Josiah Kendall
 */
class DetailsView : AppCompatActivity(){

    val presenter by inject<DetailsPresenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stat_details_view)
        presenter.setView(this)
        presenter.start("todo")
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition( R.anim.slide_out_exit, R.anim.slide_in_exit)
    }

    /**
     * Set the "historical" line chart with
     */
    fun setHistoricalCharts() {

    }

    /**
     * Set the opponent game stage comparison charts
     * @param stats An Array list of the [HeadToHeadStat] objects for each game stage
     */
    fun setVsOpponentGameStageCharts(stats : ArrayList<HeadToHeadStat>) {
        val earlyGameData = stats.earlyGame().producePieChartData(this)
        val midGameData = stats.midGame().producePieChartData(this)
        val lateGameData = stats.lateGame().producePieChartData(this)

        vsOpponentStats.setGraphs(earlyGameData, midGameData, lateGameData)
    }

    /**
     * Set the division game stage comparison charts
     * @param stats An Array list of the [HeadToHeadStat] objects for each game stage
     */
    fun setVsDivisionGameStageCharts(stats: ArrayList<HeadToHeadStat>) {
        val earlyGameData = stats.earlyGame().producePieChartData(this)
        val midGameData = stats.midGame().producePieChartData(this)
        val lateGameData = stats.lateGame().producePieChartData(this)

        vsDivisionStats.setGraphs(earlyGameData, midGameData, lateGameData)
    }


    /**
     * Show a message to the user in the form of a [Snackbar]
     *
     * @param message The message to display
     */
    fun showMessage(message : String) {
        Snackbar.make(base_root, message, Snackbar.LENGTH_LONG)
    }
}