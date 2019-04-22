package com.teamunemployment.lolanalytics.front_page;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.teamunemployment.lolanalytics.PresenterContract;
import com.teamunemployment.lolanalytics.ViewContract;

/**
 * @author Josiah Kendall
 */

public interface BaseActivityContract {

    interface Presenter extends PresenterContract {
        void showMessage(String message);
        void handleTabPress(int tab);
        void onWinRateLoaded(double winRate);
        void setUpTabFragments();
        void setUpBottomBar(AHBottomNavigation bottomBar);
    }

    interface View extends ViewContract {
        void setCorrectTabFragment(int tab);
    }
}
