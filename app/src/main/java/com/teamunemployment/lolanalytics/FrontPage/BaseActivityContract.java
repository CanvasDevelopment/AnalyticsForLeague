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
    }

    interface View extends ViewContract {
        void setCorrectTabFragment(int tab);
        void setWinRate(String winRateString);
        void setTabIconAndString(int icon, String string);
    }
}
