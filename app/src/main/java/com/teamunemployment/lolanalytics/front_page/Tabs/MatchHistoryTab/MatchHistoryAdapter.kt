package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.teamunemployment.lolanalytics.data.model.MatchIdWrapper
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.MatchHistoryCardView
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Model.BarChartModel
import com.teamunemployment.lolanalytics.front_page.Tabs.TabContract
import com.teamunemployment.lolanalytics.R
import kotlinx.android.synthetic.main.match_history_card.view.*

import java.util.ArrayList

/**
 * @author Josiah Kendall.
 *
 * Adapter for the Match History tab view.
 */
class MatchHistoryAdapter(private val matchHistoryPresenter: MatchHistoryPresenter) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), TabContract.TabAdapter {

    private lateinit var matchIds: ArrayList<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.match_history_card, parent, false)
        return MatchHistoryCardView(v, parent.context, matchHistoryPresenter)
    }

    override fun onBindViewHolder(holder : RecyclerView.ViewHolder, position: Int) {

        // Our card view instance
        val cardView = holder as MatchHistoryCardView
        setClicklistenersUp(cardView)
        // Load our value
        matchHistoryPresenter.loadCardData(matchIds[position], cardView, "todo region")
    }

    private fun setClicklistenersUp(cardView: MatchHistoryCardView) {
        cardView.itemView.cardDetailsButton.setOnClickListener({matchHistoryPresenter.onDetailsButtonClick()})
    }

    override fun getItemCount() : Int {
        return matchIds.size
    }

    fun setData(matchIds: ArrayList<String>) {
        this.matchIds = matchIds
    }

    override fun setMatchHistoryAdapterData(matchIds: ArrayList<String>) {
        this.matchIds = matchIds
    }
}
