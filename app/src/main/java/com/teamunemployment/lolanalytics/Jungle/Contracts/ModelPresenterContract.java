package com.teamunemployment.lolanalytics.Jungle.Contracts;

import com.teamunemployment.lolanalytics.Jungle.Model.AdapterPojo;
import com.teamunemployment.lolanalytics.Base.ViewFragmentContract;

import java.util.ArrayList;

/**
 * @author Josiah Kendall.
 */

public interface ModelPresenterContract {

    void start();
    void addStatToList(AdapterPojo adapterPojo);
    void addDataToAdapter(ArrayList<AdapterPojo> adapterPojos);
    void setView(ViewFragmentContract viewFragmentContract);

}
