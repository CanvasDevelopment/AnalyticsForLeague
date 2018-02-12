package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View

import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.MatchHistoryPresenter
import com.squareup.picasso.Picasso
import com.teamunemployment.lolanalytics.R
import kotlinx.android.synthetic.main.match_history_card.view.*

/**
 * @author Josiah Kendall.
 */

class MatchHistoryCardView(private val cardBase: View,
                           private val context: Context,
                           private val matchHistoryPresenter: MatchHistoryPresenter)
    : RecyclerView.ViewHolder(cardBase),
        MatchHistoryCardViewContract {
    override fun setSummaryChart(matchHistoryGameStageData: MatchHistoryGameStageData) {
        setUpPieChart(cardBase.performanceSummaryChart, matchHistoryGameStageData)
    }

    override fun setGraph1(matchHistoryGameStageData: MatchHistoryGameStageData) {
        setUpPieChart(cardBase.chart1, matchHistoryGameStageData)
    }

    override fun setGraph2(matchHistoryGameStageData: MatchHistoryGameStageData) {
        setUpPieChart(cardBase.chart2, matchHistoryGameStageData)
    }

    override fun setGraph3(matchHistoryGameStageData: MatchHistoryGameStageData) {
        setUpPieChart(cardBase.chart3, matchHistoryGameStageData)
    }

    override fun setGraph4(matchHistoryGameStageData: MatchHistoryGameStageData) {
        //  setUpPieChart(barChart4, matchHistoryGameStageData);
    }

    override fun setHeroChampIcon(champIconUrl: String) {
//        Picasso.with(context).load(champIconUrl).into(cardBase.heroChamp)
    }

    override fun setEnemyChampIcon(champIconUrl: String?) {
        Picasso.with(context).load(champIconUrl).into(cardBase.enemyChamp)
    }

    override fun setTimeStamp(timeStamp: String?) {
        // set the time stamp
    }

    override fun setChamp(champName: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setResult(win: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // This is reused code from somewhere else - need to sort this out.
    private fun setUpPieChart(pieChart: PieChart,
                              matchHistoryGameStageData: MatchHistoryGameStageData) {
        val pieDataSet = matchHistoryPresenter.fetchPieDataSet(matchHistoryGameStageData)
        pieDataSet.valueTextColor = context.resources.getColor(R.color.grey)
        pieDataSet.setColors(intArrayOf(R.color.teal, R.color.pink), context) // Would be real cool to not use context here if possible.

        val pieData = matchHistoryPresenter.fetchPieData(pieDataSet)
        pieChart.data = pieData

        // Set appearance params.
        val description = Description()
        description.text = ""
        pieChart.description = description
        pieChart.setDrawEntryLabels(false)
        pieChart.centerText = "51%"
        pieChart.legend.isEnabled = false

        // Refresh.
        pieChart.invalidate()
        pieChart.animateY(300)
    }
}
