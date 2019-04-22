package com.teamunemployment.lolanalytics.front_page.champ_menu.Model

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.squareup.picasso.Callback

import com.squareup.picasso.Picasso
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.front_page.champ_menu.ChampMenuPresenter
import com.teamunemployment.lolanalytics.data.model.ChampFrequency
import kotlinx.android.synthetic.main.champ_search_card.view.*
import java.lang.Exception

/**
 * Created by Josiah Kendall
 */

class ChampSearchCardView(private val view: View,
                          private val champMenuPresenter: ChampMenuPresenter,
                          private val context: Context) : RecyclerView.ViewHolder(view), View.OnClickListener {
    private var champ: Champ? = null

    init {
        view.setOnClickListener(this)
    }

    /**
     * Set the champ that this card/item relates to.
     * @param champ
     */
    fun setChamp(champ: Champ?) {
        this.champ = champ

        val picasso = Picasso.get()
        picasso.setIndicatorsEnabled(true)
        picasso.isLoggingEnabled = true

        if (champ == null) {
            picasso.load(R.drawable.ic_account_circle_black_24dp)
                    .placeholder(R.drawable.ic_person_white_24dp)
                    .tag("PICASSO")
                    .into(view.champImage)
            return
        }

        // does not show
        val champString = champ.title
                .replace(" ", "")
                .replace("'", "")
                .replace(".", "")
        picasso.load(champMenuPresenter.getUrl(champString))
                .placeholder(R.drawable.ic_person_white_24dp)
                .tag("PICASSO")
                .into(view.champImage)
    }

    override fun onClick(view: View) {
        //champMenuPresenter.
        // if placeholder

        champMenuPresenter.handleChampClick(champ)
    }
}
