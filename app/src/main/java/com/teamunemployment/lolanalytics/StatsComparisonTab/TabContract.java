package com.teamunemployment.lolanalytics.StatsComparisonTab;

import com.teamunemployment.lolanalytics.BasePresenterContract;
import com.teamunemployment.lolanalytics.BaseViewContract;
import com.teamunemployment.lolanalytics.StatsComparisonTab.Model.CardData;

import java.util.ArrayList;

/**
 * @author Josiah Kendall.
 *
 */
public interface TabContract {

    interface Presenter extends BasePresenterContract {
        void addDataToAdapter(ArrayList<CardData> cardDatas);
        void setView(View viewFragmentContract, int lane);
    }

    interface View extends BaseViewContract {
        void setAdapter(TabRecyclerAdapter tabRecyclerAdapter);
    }


}
