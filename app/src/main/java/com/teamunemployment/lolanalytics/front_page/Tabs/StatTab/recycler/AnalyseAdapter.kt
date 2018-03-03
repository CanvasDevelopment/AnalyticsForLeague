package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamunemployment.lolanalytics.Utils.Constant.AnalysisCardType.HALF_AD
import com.teamunemployment.lolanalytics.Utils.Constant.AnalysisCardType.FULL_AD
import com.teamunemployment.lolanalytics.Utils.Constant.AnalysisCardType.HALF_STAT
import com.teamunemployment.lolanalytics.Utils.Constant.AnalysisCardType.FULL_STAT

import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen.ViewProducer
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.AnalysePresenter


/**
 * Created by Josiah Kendall
 *
 * The adapter for our recycler view in the analyse tab
 */

class AnalyseAdapter : RecyclerView.Adapter<StatCardView>() {
    private lateinit var presenter: AnalysePresenter

    /**
     * Handles the creation of the create view holder.
     * Creates a [StatCardView], which can be one of
     * [HALF_AD] -      An advertisement that covers half the recycler
     * [FULL_AD] -      An advertisement that covers the whole recycler.
     * [HALF_STAT] -    A stat card with just one chart/stat.
     * [FULL_STAT] -    A stat card with three charts - an early, mid and late game
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatCardView {
        // create the correct view
        return presenter.createViewType(ViewProducer(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: StatCardView, position: Int) {
        presenter.onCardBinding(holder, position)
    }

    override fun getItemCount(): Int {
        return presenter.getFilterListSize()
    }

    fun setPresenter(presenter: AnalysePresenter) {
        this.presenter = presenter
    }
}
