package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.teamunemployment.lolanalytics.data.model.MatchIdWrapper
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.MatchHistoryCardView
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Model.BarChartModel
import com.teamunemployment.lolanalytics.front_page.Tabs.TabContract
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.CardData

import java.util.ArrayList

/**
 * @author Josiah Kendall.
 *
 * Adapter for the Match History tab view.
 */
class MatchHistoryAdapter(private val matchHistoryBasePresenter: MatchHistoryPresenter, private val barChartModel: BarChartModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), TabContract.TabAdapter {

    private var matchIds: ArrayList<MatchIdWrapper>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.match_history_card, parent, false)
        return MatchHistoryCardView(v, parent.context, matchHistoryBasePresenter, barChartModel)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        // Our card view instance
        val cardView = holder as MatchHistoryCardView

        if (matchIds == null) {
            throw IllegalStateException("setPlayerAnalysisAdapterData() must be called before binding adapter")
        }

        // Load our value
        cardView.loadData(matchIds!![position].matchId)
    }

    override fun getItemCount(): Int {
        return matchIds!!.size
    }

    fun setData(matchIds: ArrayList<MatchIdWrapper>) {
        this.matchIds = matchIds
    }

    override fun setMatchHistoryAdapterData(matchIds: ArrayList<MatchIdWrapper>) {
        this.matchIds = matchIds
    }

//    override fun setPlayerAnalysisAdapterData(statDefinitions: ArrayList<StatDefinition>) {
//        throw IllegalStateException("This is a matchHistoryAdapter. Please use the appropriate method.")
//    }
//
//    override fun setStatComparisonAdapterData(cardDatas: ArrayList<CardData>) {
//        throw IllegalStateException("This is a matchHistoryAdapter. Please use the appropriate method.")
//    }
}
