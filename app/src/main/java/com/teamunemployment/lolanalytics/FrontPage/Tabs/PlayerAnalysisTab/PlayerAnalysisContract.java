package com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab;

import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatCollection;
import com.teamunemployment.lolanalytics.BasePresenterContract;
import com.teamunemployment.lolanalytics.ViewContract;

/**
 * @author Josiah Kendall
 */
public class PlayerAnalysisContract {
    interface BasePresenter extends BasePresenterContract {
        void LoadStatList(int role, long summonerId);
        void LoadStatDetails(long statId, long summonerId);
        void setView(PlayerAnalysisContract.View view);
    }

    interface View extends ViewContract {
        void setStatCollectionData(StatCollection statCollection);
    }
}
