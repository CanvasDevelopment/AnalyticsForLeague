package com.teamunemployment.lolanalytics.front_page.tabs.StatTab.recycler

import android.view.View
import com.teamunemployment.lolanalytics.Utils.produceBarData
import com.teamunemployment.lolanalytics.Utils.producePieChartData
import com.teamunemployment.lolanalytics.Utils.setDefaultStyle
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.AnalysePresenter
import kotlinx.android.synthetic.main.three_stage_stat_view.view.*

/**
 * Created by Josiah Kendall
 */
class FullStatCardView(view: View, presenter: AnalysePresenter) : StatCardView(view, presenter)  {

    fun setGraph1(stat : HeadToHeadStat) {
        val barData = stat.produceBarData(presenter.getContext())
        view.earlyGameGraph.data = barData
//        view.earlyGameGraph.setDefaultStyle(barData.centreText)
    }

    fun setGraph2(stat : HeadToHeadStat) {
        val barData = stat.produceBarData(presenter.getContext())
        view.midGameGraph.data = barData
//        view.midGameGraph.setDefaultStyle(pieStat.centreText)
    }

    fun setGraph3(stat : HeadToHeadStat) {
        val barData = stat.produceBarData(presenter.getContext())
        view.lateGameGraph.data = barData
//        view.lateGameGraph.setDefaultStyle(pieStat.centreText)
    }
}