package com.teamunemployment.lolanalytics.Jungle.Model;

import com.teamunemployment.lolanalytics.Jungle.PresentationLayer.JungleAdapter;

import java.util.ArrayList;

/**
 * @author Josiah Kendall
 *
 * The model layer for our jungle fragment view.
 */
public class JungleModel {

    public ArrayList<JungleAdapterPojo> getCardPojos() {

        // For test purposes atm.
        JungleAdapterPojo jungleAdapterPojo = new JungleAdapterPojo();
        JungleAdapterPojo jungleAdapterPojo1 = new JungleAdapterPojo();
        JungleAdapterPojo jungleAdapterPojo2 = new JungleAdapterPojo();

        ArrayList<JungleAdapterPojo> results = new ArrayList<>();
        results.add(jungleAdapterPojo);
        results.add(jungleAdapterPojo1);
        results.add(jungleAdapterPojo2);
        return results;
    }

}
