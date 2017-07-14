package com.teamunemployment.lolanalytics.FrontPage;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.teamunemployment.lolanalytics.PresenterContract;
import com.teamunemployment.lolanalytics.ViewContract;

/**
 * @author Josiah Kendall
 */

public interface BaseActivityContract {

    interface Presenter extends PresenterContract {
        void handleTabPress(int tab);
        void onWinRateLoaded(double winRate);
        void setUpTabFragments();
        void setUpBottomBar(AHBottomNavigation bottomBar);
    }

    interface View extends ViewContract {
        void setCorrectTabFragment(int tab);
        void setWinRate(double winRate);
        void setTabIcon(int icon);
        void setRoleName(String string);
    }
}
