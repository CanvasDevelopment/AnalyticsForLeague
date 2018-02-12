package com.teamunemployment.lolanalytics.front_page.Search.Model

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View

import com.squareup.picasso.Picasso
import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.front_page.Search.SearchPresenter
import com.teamunemployment.lolanalytics.R
import kotlinx.android.synthetic.main.champ_search_card.view.*

/**
 * Created by Josiah Kendall
 */

class ChampSearchCardView(private val view: View, private val searchPresenter: SearchPresenter, private val context: Context) : RecyclerView.ViewHolder(view), View.OnClickListener {
    private var champ: Champ? = null


    init {
        view.setOnClickListener(this)
    }

    /**
     * Set the champ that this card/item relates to.
     * @param champ
     */
    fun SetChamp(champ: Champ) {
        this.champ = champ
        if (champ.champUrl.isEmpty()) {
            // set our clear filter
            view.champImage.setImageResource(R.drawable.ic_account_circle_black_24dp)
            return
        }
        Picasso.with(context).load(champ.champId).into(view.champImage)
    }

    override fun onClick(view: View) {
        //searchPresenter.
        searchPresenter.handleChampClick(champ)
    }
}
