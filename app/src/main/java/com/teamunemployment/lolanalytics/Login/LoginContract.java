package com.teamunemployment.lolanalytics.Login;

import com.teamunemployment.lolanalytics.BasePresenterContract;
import com.teamunemployment.lolanalytics.BaseViewContract;

/**
 * @author Josiah Kendall.
 */
public interface LoginContract {
    interface Presenter extends BasePresenterContract {
        void LoginTestUser();
        void setView(LoginContract.LoginView loginView);
    }

    interface LoginView extends BaseViewContract {
        void launchHomeActivity();
    }

}
