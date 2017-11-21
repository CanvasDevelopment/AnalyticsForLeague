package com.teamunemployment.lolanalytics.login;

import android.text.Editable;
import android.widget.ArrayAdapter;

import com.teamunemployment.lolanalytics.PresenterContract;
import com.teamunemployment.lolanalytics.ViewContract;

/**
 * @author Josiah Kendall.
 */
public interface LoginContract {
    interface Presenter extends PresenterContract {
        void loginTestUser();
        void setView(LoginContract.LoginView loginView);
        void handleLogin();
        void handleLoginResult(long loginResult);
    }

    interface LoginView extends ViewContract {
        void launchHomeActivity();
        void setRegionSpinnerAdapter(ArrayAdapter<CharSequence> adapter);
        String getUserName();
        String getRegion();
    }

}
