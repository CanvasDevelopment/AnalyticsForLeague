package com.teamunemployment.lolanalytics.FrontPage.Search;

import com.teamunemployment.lolanalytics.Data.model.Champ;
import com.teamunemployment.lolanalytics.R;

import java.util.ArrayList;

/**
 * Created by Josiah Kendall
 */

public class SearchInteractor {

    private Champ currentFilteredChamp;

    public void GetFavouriteChamps(SearchPresenter searchPresenter) {
        ArrayList<Champ> champs = new ArrayList<>();
        Champ champ = new Champ();
        champ.setChampName("vi");
        champ.setChampUrl("http://icons.iconarchive.com/icons/fazie69/league-of-legends/256/vi-icon.png");
        champ.setChampResId(R.drawable.khasquare);
        Champ champ2 = new Champ();
        champ2.setChampName("vi");
        champ2.setChampResId(R.drawable.sejuanisquare);
        champ2.setChampUrl("http://icons.iconarchive.com/icons/fazie69/league-of-legends/256/vi-icon.png");
        Champ champ3 = new Champ();
        champ3.setChampName("vi");
        champ3.setChampResId(R.drawable.nasus);
        champ3.setChampUrl("http://icons.iconarchive.com/icons/fazie69/league-of-legends/256/vi-icon.png");
        Champ champ4 = new Champ();
        champ4.setChampName("vi");
        champ4.setChampResId(R.drawable.shyvanasquare);
        champ4.setChampUrl("http://icons.iconarchive.com/icons/fazie69/league-of-legends/256/vi-icon.png");
        Champ champ5 = new Champ();
        champ5.setChampName("vi");
        champ5.setChampResId(R.drawable.vi);
        champ5.setChampUrl("http://icons.iconarchive.com/icons/fazie69/league-of-legends/256/vi-icon.png");
        Champ champ6 = new Champ();
        champ6.setChampName("vi");
        champ6.setChampUrl("http://icons.iconarchive.com/icons/fazie69/league-of-legends/256/vi-icon.png");
        Champ champ7 = new Champ();
        champ7.setChampName("vi");
        champ7.setChampUrl("http://icons.iconarchive.com/icons/fazie69/league-of-legends/256/vi-icon.png");
        Champ champ8 = new Champ();
        champ8.setChampName("vi");
        champ8.setChampUrl("http://icons.iconarchive.com/icons/fazie69/league-of-legends/256/vi-icon.png");
//        champs.add(champ8);
        champs.add(champ);
        champs.add(champ2);
        champs.add(champ3);
        champs.add(champ4);
        champs.add(champ5);
//        champs.add(champ6);
//        champs.add(champ7);
        searchPresenter.SetChampRequestResponse(champs);
    }

    public Champ GetCurrentSetChamp() {
        return currentFilteredChamp;
    }

    public void SetCurrentChamp(Champ champ) {
        this.currentFilteredChamp = champ;
    }
}
