package com.teamunemployment.lolanalytics.login_page.sign_in;

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
        void requestUserRegistration();
        void handleRegisterUserResult(Integer code, String summonerId, String region);
    }

    interface LoginView extends ViewContract {
        void launchOnboardingActivity(String summonerId);
        void setRegionSpinnerAdapter(ArrayAdapter<CharSequence> adapter);
        String getUserName();
        String getRegion();
        void showLoginProgressSpinner();
        void showLoginButton();
        void hideProgressSpinner();
        void hideLoginButton();
        void disableProceed();
        void enableProceed();
        void launchMainScreen(String summonerId);
    }

}
