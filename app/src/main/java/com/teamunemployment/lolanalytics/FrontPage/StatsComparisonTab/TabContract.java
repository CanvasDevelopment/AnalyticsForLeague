package com.teamunemployment.lolanalytics.FrontPage.StatsComparisonTab;

import com.teamunemployment.lolanalytics.PresenterContract;
import com.teamunemployment.lolanalytics.ViewContract;
import com.teamunemployment.lolanalytics.StatsComparisonTab.Model.CardData;

import java.util.ArrayList;

/**
 * @author Josiah Kendall.
 *
 */
public interface TabContract {

    interface Presenter extends PresenterContract {
        void addDataToAdapter(ArrayList<CardData> cardDatas);
        void setView(View viewFragmentContract, int lane);
    }

    interface View extends ViewContract {
        void setAdapter(TabRecyclerAdapter tabRecyclerAdapter);
        void setLoadingVisibile(boolean visibile);
        void setErrorMessage(String errorMessage);
    }


}
