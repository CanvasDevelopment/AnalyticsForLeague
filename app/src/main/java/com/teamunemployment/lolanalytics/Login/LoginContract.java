package com.teamunemployment.lolanalytics.Login;

import android.widget.ArrayAdapter;

import com.teamunemployment.lolanalytics.BasePresenterContract;
import com.teamunemployment.lolanalytics.BaseViewContract;

/**
 * @author Josiah Kendall.
 */
public interface LoginContract {
    interface Presenter extends BasePresenterContract {
        void LoginTestUser();
        void setView(LoginContract.LoginView loginView);
        void handleLogin(String username, String region);
        void setLoginResult(long loginResult);
    }

    interface LoginView extends BaseViewContract {
        void launchHomeActivity();
        void setRegionSpinnerAdapter(ArrayAdapter<CharSequence> adapter);
    }

}
