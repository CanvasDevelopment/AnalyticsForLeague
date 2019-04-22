package com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.Utils.*
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen.tabs.StatDetailsTabAdapter
import kotlinx.android.synthetic.main.stat_details_view.*
import org.koin.android.ext.android.inject

/**
 * Created by Josiah Kendall
 */
class DetailsView : AppCompatActivity(){

    val presenter by inject<DetailsPresenter>()
    private var tabAdapter: StatDetailsTabAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stat_details_view)
        presenter.setView(this)
        setUpTabs()
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


    private fun setUpTabs() {
        tabAdapter = StatDetailsTabAdapter(supportFragmentManager)
        tabAdapter!!.statName = "creeps" // todo
        statDetailsViewPager.adapter = tabAdapter

        statDetailsTabs.setupWithViewPager(statDetailsViewPager)
    }

    /**
     * Set the opponent game stage comparison charts
     * @param stats An Array list of the [HeadToHeadStat] objects for each game stage
     */
    fun setVsOpponentGameStageCharts(stats : ArrayList<HeadToHeadStat>) {
        val earlyGameData = stats.earlyGame().producePieChartData(this)
        val midGameData = stats.midGame().producePieChartData(this)
        val lateGameData = stats.lateGame().producePieChartData(this)

//        vsOpponentStats.setGraphs(earlyGameData, midGameData, lateGameData)
    }

    /**
     * Set the division game stage comparison charts
     * @param stats An Array list of the [HeadToHeadStat] objects for each game stage
     */
    fun setVsDivisionGameStageCharts(stats: ArrayList<HeadToHeadStat>) {
        val earlyGameData = stats.earlyGame().producePieChartData(this)
        val midGameData = stats.midGame().producePieChartData(this)
        val lateGameData = stats.lateGame().producePieChartData(this)

//        vsDivisionStats.setGraphs(earlyGameData, midGameData, lateGameData)
    }


    /**
     * Show a message to the user in the form of a [Snackbar]
     *
     * @param message The message to display
     */
    fun showMessage(message : String) {
        Snackbar.make(stat_details_root, message, Snackbar.LENGTH_LONG)
    }
}