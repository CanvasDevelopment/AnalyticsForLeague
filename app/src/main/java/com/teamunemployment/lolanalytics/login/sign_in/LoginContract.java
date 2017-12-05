package com.teamunemployment.lolanalytics.login.sign_in;

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
        void requestSync();
        void handleSyncResult(Integer code, long loginResult);
    }

    interface LoginView extends ViewContract {
        void launchOnboardingActivity();
        void setRegionSpinnerAdapter(ArrayAdapter<CharSequence> adapter);
        String getUserName();
        String getRegion();
    }

}
