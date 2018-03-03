package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.AnalysePresenter
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.AnalyseTabContract
import kotlinx.android.synthetic.main.analysis_card_item.view.*
import java.text.FieldPosition

/**
 * Created by Josiah Kendall
 */
open class StatCardView(val view: View, val presenter: AnalysePresenter) : RecyclerView.ViewHolder(view), StatCardContract {

    override fun setTitle(title: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // There is a "position" already on the ViewHolder, but I am not sure that it relates to what we
    // want. It also doesnt seem super testable, so I'm using my own. Thus the underscore.
    private var _position : Int = -1
    init {
        view.card_details.setOnClickListener { handleClick() }
    }

    private fun handleClick() {
        presenter.handleItemClick(_position)
    }

    override fun onStatLoaded(stats: ArrayList<HeadToHeadStat>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun setCardPosition(position: Int) {
        _position = position
    }
}