package com.teamunemployment.lolanalytics.front_page.Search

import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.R

import java.util.ArrayList

/**
 * Created by Josiah Kendall
 */

class SearchInteractor {

    private var currentFilteredChamp: Champ? = null

    fun getFavouriteChamps(searchPresenter: SearchPresenter) {
        val champs = ArrayList<Champ>()
        val champ = Champ()
        champ.champName = "vi"
        champ.champUrl = "http://icons.iconarchive.com/icons/fazie69/league-of-legends/256/vi-icon.png"
        champ.setChampResId(R.drawable.khasquare)
        val champ2 = Champ()
        champ2.champName = "vi"
        champ2.setChampResId(R.drawable.sejuanisquare)
        champ2.champUrl = "http://icons.iconarchive.com/icons/fazie69/league-of-legends/256/vi-icon.png"
        val champ3 = Champ()
        champ3.champName = "vi"
        champ3.setChampResId(R.drawable.nasus)
        champ3.champUrl = "http://icons.iconarchive.com/icons/fazie69/league-of-legends/256/vi-icon.png"
        val champ4 = Champ()
        champ4.champName = "vi"
        champ4.setChampResId(R.drawable.shyvanasquare)
        champ4.champUrl = "http://icons.iconarchive.com/icons/fazie69/league-of-legends/256/vi-icon.png"
        val champ5 = Champ()
        champ5.champName = "vi"
        champ5.setChampResId(R.drawable.vi)
        champ5.champUrl = "http://icons.iconarchive.com/icons/fazie69/league-of-legends/256/vi-icon.png"
        val champ6 = Champ()
        champ6.champName = "vi"
        champ6.champUrl = "http://icons.iconarchive.com/icons/fazie69/league-of-legends/256/vi-icon.png"
        val champ7 = Champ()
        champ7.champName = "vi"
        champ7.champUrl = "http://icons.iconarchive.com/icons/fazie69/league-of-legends/256/vi-icon.png"
        val champ8 = Champ()
        champ8.champName = "vi"
        champ8.champUrl = "http://icons.iconarchive.com/icons/fazie69/league-of-legends/256/vi-icon.png"
        //        champs.add(champ8);
        champs.add(champ)
        champs.add(champ2)
        champs.add(champ3)
        champs.add(champ4)
        champs.add(champ5)
        //        champs.add(champ6);
        //        champs.add(champ7);
        searchPresenter.setChampRequestResponse(champs)
    }

    fun GetCurrentSetChamp(): Champ? {
        return currentFilteredChamp
    }

    fun setCurrentChamp(champ: Champ?) {
        this.currentFilteredChamp = champ
    }
}
