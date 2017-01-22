package com.teamunemployment.lolanalytics.Login;

import javax.inject.Inject;

/**
 * @author Josiah Kendall
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.LoginView view;

    @Inject
    public LoginPresenter() {

    }

    @Override
    public void start() {
        // Set up login.
    }

    @Override
    public void handleError(Throwable e) {
        view.showMessage("Sorry, an error occurred. Please try again");
    }

    @Override
    public void LoginTestUser() {
        view.launchHomeActivity();
    }

    @Override
    public void setView(LoginContract.LoginView loginView) {
        this.view = loginView;
    }


}
