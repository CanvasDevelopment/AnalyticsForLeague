package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.recycler

import android.view.View
import com.teamunemployment.lolanalytics.Utils.producePieChartData
import com.teamunemployment.lolanalytics.Utils.setDefaultStyle
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.AnalysePresenter
import kotlinx.android.synthetic.main.three_stage_stat_view.view.*

/**
 * Created by Josiah Kendall
 */
class FullStatCardView(view: View, presenter: AnalysePresenter) : StatCardView(view, presenter)  {

    fun setGraph1(stat : HeadToHeadStat) {
        val pieStat = stat.producePieChartData(presenter.getContext())
        view.earlyGameGraph.data = pieStat.pieData
        view.earlyGameGraph.setDefaultStyle(pieStat.centreText)
    }

    fun setGraph2(stat : HeadToHeadStat) {
        val pieStat = stat.producePieChartData(presenter.getContext())
        view.midGameGraph.data = pieStat.pieData
        view.midGameGraph.setDefaultStyle(pieStat.centreText)
    }

    fun setGraph3(stat : HeadToHeadStat) {
        val pieStat = stat.producePieChartData(presenter.getContext())
        view.lateGameGraph.data = pieStat.pieData
        view.lateGameGraph.setDefaultStyle(pieStat.centreText)
    }
}