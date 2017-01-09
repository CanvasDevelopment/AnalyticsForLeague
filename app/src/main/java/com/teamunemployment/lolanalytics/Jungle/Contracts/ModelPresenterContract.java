package com.teamunemployment.lolanalytics.Jungle.Contracts;

import com.teamunemployment.lolanalytics.Jungle.Model.JungleAdapterPojo;
import com.teamunemployment.lolanalytics.base.ViewFragmentContract;

import java.util.ArrayList;

/**
 * @author Josiah Kendall.
 */

public interface ModelPresenterContract {

    void start();
    void addStatToList(JungleAdapterPojo jungleAdapterPojo);
    void addDataToAdapter(ArrayList<JungleAdapterPojo> adapterPojos);
    void setView(ViewFragmentContract viewFragmentContract);

}
