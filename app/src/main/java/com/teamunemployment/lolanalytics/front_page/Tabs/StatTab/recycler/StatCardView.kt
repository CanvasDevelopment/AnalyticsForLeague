package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.AnalysePresenter
import kotlinx.android.synthetic.main.analyse_card_item.view.*

/**
 * Created by Josiah Kendall
 */
open class StatCardView(val view: View, val presenter: AnalysePresenter) : RecyclerView.ViewHolder(view), StatCardContract {

    override fun setTitle(title: String) {
        view.analyseCardTitle.text = title
    }

    // There is a "position" already on the ViewHolder, but I am not sure that it relates to what we
    // want. It also doesnt seem super testable, so I'm using my own. Thus the underscore.
    private var _position : Int = -1
    init {
        view.cardDetailsButton.setOnClickListener { handleDetailsClick() }
    }

    private fun handleDetailsClick() {
        presenter.handleItemClick(_position)
    }

    override fun onStatLoaded(stats: ArrayList<HeadToHeadStat>) {

    }

    fun setCardPosition(position: Int) {
        _position = position
    }

    fun getCardPosition() : Int {
        return _position
    }

    fun isAd() : Boolean {
        return getCardPosition() == com.teamunemployment.lolanalytics.Utils.Constant.AnalysisCardType.FULL_AD
                || getCardPosition() == com.teamunemployment.lolanalytics.Utils.Constant.AnalysisCardType.HALF_AD
    }
}