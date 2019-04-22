package com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View

import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.MatchHistoryPresenter
import com.squareup.picasso.Picasso
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.Utils.produceBarData
import com.teamunemployment.lolanalytics.Utils.producePieChartData
import com.teamunemployment.lolanalytics.Utils.setDefaultStyle
import com.teamunemployment.lolanalytics.Utils.setSimpleStyle
import kotlinx.android.synthetic.main.card_item.view.*
import kotlinx.android.synthetic.main.match_history_card.view.*
import kotlinx.android.synthetic.main.match_history_details_panel.view.*
import kotlinx.android.synthetic.main.three_stage_stat_view.view.*

/**
 * @author Josiah Kendall.
 */

class MatchHistoryCardView(private val cardBase: View,
                           private val context: Context,
                           private val matchHistoryPresenter: MatchHistoryPresenter)
    : RecyclerView.ViewHolder(cardBase),
        MatchHistoryCardViewContract {

    override fun setChampPlaceHolders() {
        val loading = "Loading"
        cardBase.performanceSummaryChart.setNoDataText(loading)
        cardBase.earlyGameGraph.setNoDataText(loading)
        cardBase.midGameGraph.setNoDataText(loading)
        cardBase.lateGameGraph.setNoDataText(loading)
    }

    var _detailsUrl : String = ""

    override fun getDetailsUrl(): String {
        return _detailsUrl
    }

    override fun setDetailsUrl(detailsUrl: String) {
        this._detailsUrl = detailsUrl
    }

    override fun setSummaryChart(headToHeadStat: HeadToHeadStat) {
        val result = headToHeadStat.produceBarData(context)
        cardBase.performanceSummaryChart.data = result
        cardBase.performanceSummaryChart.setDefaultStyle()
    }

    override fun setGraph1(headToHeadStat: HeadToHeadStat) {
// todo grey stuff  val greyDisplay = headToHeadStat.enemyStatValue == 0f || headToHeadStat.heroStatValue == 0f
        val result = headToHeadStat.produceBarData(context)
        cardBase.earlyGameGraph.data = result
        cardBase.earlyGameGraph.setDefaultStyle()
    }

    override fun setGraph2(headToHeadStat: HeadToHeadStat) {
        val result = headToHeadStat.produceBarData(context)
        cardBase.midGameGraph.data = result
        cardBase.midGameGraph.setDefaultStyle()

    }

    override fun setGraph3(headToHeadStat: HeadToHeadStat) {
        val result = headToHeadStat.produceBarData(context)
        cardBase.lateGameGraph.data = result
        cardBase.lateGameGraph.setDefaultStyle()

    }

    override fun setGraph4(HeadToHeadStat: HeadToHeadStat) {
        //  setUpPieChart(barChart4, HeadToHeadStat);
    }

    override fun setHeroChampIcon(champIconUrl: String) {
//        Picasso.with(context).load(champIconUrl).into(cardBase.heroChamp)
    }

    override fun setEnemyChampIcon(champIconUrl: String) {
        Picasso.get().load(champIconUrl).into(cardBase.enemyChamp)
    }

    override fun setTimeStamp(timeStamp: String) {
        // set the time stamp
        cardBase.timeAgo.text = timeStamp
//        val timestampString = matchHistoryPresenter.produceTimestampString(timeStamp)
    }

    override fun setChamp(champName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setResult(result: String) {
        cardBase.resultAsChamp.text = result
    }

    fun clear() {
        cardBase.resultAsChamp.text = ""
        cardBase.timeAgo.text = "..."
        cardBase.lateGameGraph.clear()
        cardBase.midGameGraph.clear()
        cardBase.earlyGameGraph.clear()
        cardBase.performanceSummaryChart.clear()
    }
}
