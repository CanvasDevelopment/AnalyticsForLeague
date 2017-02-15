package com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab;

import com.teamunemployment.lolanalytics.BasePresenterContract;
import com.teamunemployment.lolanalytics.ViewContract;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.CardData;

import java.util.ArrayList;

/**
 * @author Josiah Kendall.
 *
 */
public interface TabContract {

    interface BasePresenter extends BasePresenterContract {
        void addDataToAdapter(ArrayList<CardData> cardDatas);
        void setView(View viewFragmentContract, int lane);
    }

    interface View extends ViewContract {
        void setAdapter(TabRecyclerAdapter tabRecyclerAdapter);
        void setLoadingVisibile(boolean visibile);
        void setErrorMessage(String errorMessage);
    }


}
