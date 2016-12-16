package com.teamunemployment.lolanalytics.Jungle.Contracts;

import com.teamunemployment.lolanalytics.Jungle.Model.JungleAdapterPojo;

import java.util.ArrayList;

/**
 * @author Josiah Kendall.
 */

public interface ModelPresenterContract {

    void addStatToList(JungleAdapterPojo jungleAdapterPojo);
    void addDataToAdapter(ArrayList<JungleAdapterPojo> adapterPojos);

}
