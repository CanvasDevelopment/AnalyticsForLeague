package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View

import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.MatchHistoryPresenter
import com.squareup.picasso.Picasso
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Model.BarChartModel
import kotlinx.android.synthetic.main.match_history_card.view.*

/**
 * @author Josiah Kendall.
 */

class MatchHistoryCardView(private val cardBase: View,
                           private val context: Context,
                           private val matchHistoryBasePresenter: MatchHistoryPresenter,
                           private val barChartModel: BarChartModel) : RecyclerView.ViewHolder(cardBase), MatchHistoryCardViewContract {

    // TODO put this in correct place
    fun loadData(matchId: Long) {
        matchHistoryBasePresenter.loadCardData(matchId, this)
    }


    override fun setGraph1(cardData: CardData) {
        setUpBarChart(cardBase.chart1, cardData)
    }

    override fun setGraph2(cardData: CardData) {
        setUpBarChart(cardBase.chart2, cardData)
    }

    override fun setGraph3(cardData: CardData) {
        setUpBarChart(cardBase.chart3, cardData)
    }

    override fun setGraph4(cardData: CardData) {
        //  setUpBarChart(barChart4, cardData);
    }


    override fun setHeroChampIcon(champIconUrl: String) {
        Picasso.with(context).load(champIconUrl).into(cardBase.heroChamp)
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
    private fun setUpBarChart(barChart: BarChart, cardData: CardData) {
////        val barDataSet = barChartModel.FetchBarDataSet(cardData)
//        barDataSet.valueTextColor = context.resources.getColor(R.color.grey)
//        barDataSet.setColors(intArrayOf(R.color.teal, R.color.pink), context) // Would be real cool to not use context here if possible.
//
//        val barData = barChartModel.FetchBarData(barDataSet)
//        barChart.data = barData
//
//        // Below here is just setting the appearance of the graphs - no logic
//        // X axis - horizontal
//        val xAxis = barChart.xAxis
//        xAxis.setDrawGridLines(false)
//        xAxis.setDrawLabels(false)
//        xAxis.setDrawAxisLine(false)

        // Left axis
        val yAxis = barChart.axisLeft
        yAxis.axisMinimum = 0f
        yAxis.setDrawLabels(false)
        yAxis.setDrawGridLines(false)
        yAxis.isEnabled = false

        // Right axis
        val yAxis2 = barChart.axisRight
        yAxis2.setDrawLabels(false)
        yAxis2.setDrawGridLines(false)
        yAxis2.isEnabled = false
        yAxis2.setDrawAxisLine(false)

        // Set appearance params.
        val description = Description()
        description.text = ""
        barChart.description = description
        barChart.setFitBars(false)
        barChart.isHighlightPerTapEnabled = true
        barChart.setDrawGridBackground(false)
        barChart.isDoubleTapToZoomEnabled = false
        barChart.isHighlightFullBarEnabled = false
        barChart.isDragEnabled = false
        barChart.setDrawBorders(false)
        barChart.setDrawMarkers(false)
        barChart.setTouchEnabled(false)
        barChart.legend.isEnabled = false

        // Refresh.
        barChart.invalidate()
        barChart.animateY(300)
    }
}
