package com.teamunemployment.lolanalytics.front_page.champ_menu

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.teamunemployment.lolanalytics.front_page.champ_menu.Model.ChampSearchCardView
import com.teamunemployment.lolanalytics.front_page.champ_menu.Model.OffSetViewHolder
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.Utils.Constant
import com.teamunemployment.lolanalytics.Utils.Constant.ViewType.OFFSET_VIEW
import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.data.model.ChampFrequency

import java.util.ArrayList

/**
 * Created by Josiah Kendall
 */

class ChampMenuListAdapter(private val champMenuPresenter: ChampMenuPresenter) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val champsOrigin: ArrayList<Champ> = ArrayList()

    private val champs = ArrayList<Champ>()
    private var filter : String? = null

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
        return ChampSearchCardView(v, champMenuPresenter, parent.context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is OffSetViewHolder) {
            return
        }

        val champ = champs[position]
        val champSearchCardView = holder as ChampSearchCardView
        if (champ.title.isEmpty()) {
            champSearchCardView.setChamp(null)
            return
        }

        if (getItemViewType(position) != OFFSET_VIEW) {
            champSearchCardView.setChamp(champ)
        }
    }

    fun setData(champs: ArrayList<Champ>) {
        champsOrigin.clear()
        champsOrigin.addAll(champs)
        this.champs.clear()
        this.champs.addAll(champs)
    }

    /**
     * Apply a string filter to the list of champs. This will filter the champs in the
     * [champs] list, while leaving the [champsOrigin] untouched.
     */
    fun filter(filter : String) {
        this.filter = filter
        champs.clear()
        val offSetChamp = Champ("","","","","","",0,"0L")
        champs.add(0, offSetChamp)
        champs.addAll(champsOrigin.filter {
            s -> s.title.toLowerCase().startsWith(filter.toLowerCase())
        })

        notifyDataSetChanged()
    }


    fun clearFilter() {
        this.filter = ""
        champs.clear()
        champs.addAll(champsOrigin)

        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return champs.size
    }

    fun getChamps() : ArrayList<Champ> {
        return champs
    }
}
