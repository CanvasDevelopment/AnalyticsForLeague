package com.teamunemployment.lolanalytics.front_page.Search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.front_page.Search.Model.ChampSearchCardView
import com.teamunemployment.lolanalytics.front_page.Search.Model.OffSetViewHolder
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.Utils.Constant

import java.util.ArrayList

/**
 * Created by Josiah Kendall
 */

class SearchListAdapter(private val searchPresenter: SearchPresenter) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var champs: ArrayList<Champ>? = null

    override fun getItemViewType(position: Int): Int {

        return if (position == 0) {
            Constant.ViewType.OFFSET_VIEW
        } else Constant.ViewType.STANDARD_VIEW
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == Constant.ViewType.OFFSET_VIEW) {
            // set up offset
            val v = LayoutInflater.from(parent.context).inflate(R.layout.offset_champ_view, parent, false)
            return OffSetViewHolder(v)
        }

        val v = LayoutInflater.from(parent.context).inflate(R.layout.champ_search_card, parent, false)
        return ChampSearchCardView(v, searchPresenter, parent.context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is OffSetViewHolder) {
            return
        }

        if (champs == null) {
            throw IllegalStateException("Champs array has not been set. SetData() must be called before views are bound")
        }

        val champ = champs!![position]
        val champSearchCardView = holder as ChampSearchCardView
        champSearchCardView.SetChamp(champ)
    }

    fun SetData(champs: ArrayList<Champ>) {
        this.champs = champs
    }

    override fun getItemCount(): Int {
        return champs!!.size
    }
}
