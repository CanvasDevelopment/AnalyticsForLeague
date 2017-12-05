package com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab;

import com.teamunemployment.lolanalytics.PresenterContract;
import com.teamunemployment.lolanalytics.front_page.Tabs.TabContract;
import com.teamunemployment.lolanalytics.ViewContract;

/**
 * @author Josiah Kendall
 */
public class PlayerAnalysisContract {
    interface Presenter extends PresenterContract {
        void LoadStatList(int role, long summonerId);
        void LoadStatDetails(int statId, long summonerId, PlayerAnalysisCardViewContract cardViewContract);
        void setView(TabContract.View view);
    }

    interface View extends ViewContract {
        void setAdapter(PlayerAnalysisAdapter adapter);
        void setRole(int role);
    }
}
