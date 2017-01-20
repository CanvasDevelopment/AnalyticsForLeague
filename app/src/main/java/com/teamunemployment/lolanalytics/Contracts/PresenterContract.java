package com.teamunemployment.lolanalytics.Contracts;

import com.teamunemployment.lolanalytics.Jungle.Model.CardData;
import com.teamunemployment.lolanalytics.Base.ViewFragmentContract;

import java.util.ArrayList;

/**
 * @author Josiah Kendall.
 */

public interface PresenterContract {

    void start(int lane);
    void addDataToAdapter(ArrayList<CardData> cardDatas);
    void setView(ViewFragmentContract viewFragmentContract, int lane);
    void handleError(Throwable e);

}
