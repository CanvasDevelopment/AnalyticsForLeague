package com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.MatchHistoryCardView
import com.teamunemployment.lolanalytics.front_page.tabs.TabContract
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.MatchIdentifier
import kotlinx.android.synthetic.main.match_history_card.view.*

import java.util.ArrayList

/**
 * @author Josiah Kendall.
 *
 * Adapter for the Match History tab view.
 */
class MatchHistoryRecycleViewAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>(), TabContract.TabAdapter {

    private val matchIds: ArrayList<MatchIdentifier> = ArrayList()
    lateinit var matchHistoryPresenter : MatchHistoryPresenter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.match_history_card, parent, false)
        return MatchHistoryCardView(v, parent.context, matchHistoryPresenter)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        val cardView = holder as MatchHistoryCardView
        cardView.clear()
        super.onViewRecycled(holder)
    }

    override fun onBindViewHolder(holder : RecyclerView.ViewHolder, position: Int) {
        // Our card view instance
        val cardView = holder as MatchHistoryCardView
        cardView.setChampPlaceHolders()
        setClicklistenersUp(cardView)
        val matchIdentifier = matchIds[position]
        if (position > (matchIds.size - 5)) {
            matchHistoryPresenter.loadMoreDataForRole(matchIds.size)
        }
        matchHistoryPresenter.loadCardData(matchIdentifier, cardView, "todo region")
    }

    private fun setClicklistenersUp(cardView: MatchHistoryCardView) {
        // construct the url
        cardView.itemView.cardDetailsButton.setOnClickListener({matchHistoryPresenter.onDetailsButtonClick(cardView.getDetailsUrl())})
    }

    override fun getItemCount() : Int {
        return matchIds.size
    }

    fun addData(matchIds: ArrayList<MatchIdentifier>) {
        this.matchIds.addAll(matchIds)
        notifyDataSetChanged()
    }

    fun clearData() {
        this.matchIds.clear()
    }
}
