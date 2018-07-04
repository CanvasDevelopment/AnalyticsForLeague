package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.Utils.Constant.AnalysisCardType.HALF_AD
import com.teamunemployment.lolanalytics.Utils.Constant.AnalysisCardType.FULL_AD
import com.teamunemployment.lolanalytics.Utils.Constant.AnalysisCardType.HALF_STAT
import com.teamunemployment.lolanalytics.Utils.Constant.AnalysisCardType.FULL_STAT

import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen.ViewProducer
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.AnalysePresenter
import com.teamunemployment.lolanalytics.front_page.Tabs.TabContract
import java.util.ArrayList


/**
 * Created by Josiah Kendall
 *
 * The adapter for our recycler view in the analyse tab
 * 138 metres 13.8
 * 1.2 offloads = 2.4
 * 0.5 line breaks = 2
 * 35 tackles = 35
 * 6 tb's = 18
 *
 * 0.5 errors = - 1
 * 2.2 missedtackles = - 2.4
 */

class AnalyseAdapter(private val viewProducer: ViewProducer) : RecyclerView.Adapter<StatCardView>(), TabContract.TabAdapter{

    private lateinit var presenter: AnalysePresenter



    /**
     * Handles the creation of the create view holder.
     * Creates a [StatCardView], which can be one of
     * [HALF_AD] -      An advertisement that covers half the recycler
     * [FULL_AD] -      An advertisement that covers the whole recycler.
     * [HALF_STAT] -    A stat cardUrl with just one chart/stat.
     * [FULL_STAT] -    A stat cardUrl with three charts - an early, mid and late game
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatCardView {
        // create the correct view

        return presenter.createViewType(viewProducer, parent, viewType)
    }

    override fun onBindViewHolder(holder: StatCardView, position: Int) {
        presenter.onCardBinding(holder, position)
    }

    override fun getItemViewType(position: Int): Int {
        return presenter.getItem(position).type
    }

    override fun getItemCount(): Int {
        return presenter.getFilterListSize()
    }

    fun setPresenter(presenter: AnalysePresenter) {
        this.presenter = presenter
    }
}
