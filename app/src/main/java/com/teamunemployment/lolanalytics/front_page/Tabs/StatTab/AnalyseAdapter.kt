package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.AnalyseCardViewHolder
import com.teamunemployment.lolanalytics.R

/**
 * Created by Josiah Kendall
 */

class AnalyseAdapter : RecyclerView.Adapter<AnalyseCardViewHolder>(), AnalyseTabContract.Adapter {

    private lateinit var presenter: AnalyseTabContract.Presenter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalyseCardViewHolder {
        return AnalyseCardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.analyse_card_item, parent, false), presenter!!)
    }

    override fun onBindViewHolder(holder: AnalyseCardViewHolder, position: Int) {
        presenter.onCardBinding(holder, position)
    }

    override fun getItemCount(): Int {
        return presenter.getFilterListSize()
    }

    override fun setPresenter(presenter: AnalyseTabContract.Presenter) {
        this.presenter = presenter
    }
}
