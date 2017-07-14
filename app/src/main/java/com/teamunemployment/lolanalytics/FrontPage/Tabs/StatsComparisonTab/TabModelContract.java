package com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab;

import com.teamunemployment.lolanalytics.PresenterContract;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.TabContract;
import com.teamunemployment.lolanalytics.ViewContract;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.CardData;

import java.util.ArrayList;

/**
 * @author Josiah Kendall.
 *
 */
public interface TabModelContract {

    interface Presenter extends PresenterContract {
        void addDataToAdapter(ArrayList<CardData> cardDatas);
        void setView(TabContract.View viewFragmentContract, int lane);
    }

    interface View extends ViewContract {
        void setAdapter(TabRecyclerAdapter tabRecyclerAdapter);
        void setLoadingVisibile(boolean visibile);
        void setErrorMessage(String errorMessage);
    }


}
