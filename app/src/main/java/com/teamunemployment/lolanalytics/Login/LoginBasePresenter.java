package com.teamunemployment.lolanalytics.Login;

import android.widget.ArrayAdapter;

import com.teamunemployment.lolanalytics.R;

import javax.inject.Inject;

/**
 * @author Josiah Kendall
 */
public class LoginBasePresenter implements LoginContract.BasePresenter {

    private LoginContract.LoginView view;

    private ArrayAdapterFactory arrayAdapterFactory;
    private LoginModel loginModel;

    @Inject
    public LoginBasePresenter(ArrayAdapterFactory arrayAdapterFactory, LoginModel loginModel) {
        this.arrayAdapterFactory = arrayAdapterFactory;
        this.loginModel = loginModel;
    }

    @Override
    public void start() {
        setRegionAdapter();
    }

    @Override
    public void handleError(Throwable e) {
        view.showMessage("Sorry, an error occurred. Please try again");
    }

    @Override
    public void restart() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }


    @Override
    public void LoginTestUser() {
        view.launchHomeActivity();
    }

    @Override
    public void setView(LoginContract.LoginView loginView) {
        this.view = loginView;
        start();
    }

    @Override
    public void handleLogin(String username, String region) {
        loginModel.requestLoginAttempt(username, region, this);
    }

    @Override
    public void setLoginResult(long loginResult) {

        if (loginResult == -1) {
            view.showMessage("An error occurred logging in. Please check the details you entered and try again.");
        } else if (loginResult > 0){
            view.launchHomeActivity();
        }
    }

    private void setRegionAdapter() {
        ArrayAdapter<CharSequence> adapter = arrayAdapterFactory.getArrayAdapter(R.array.regions_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.region_spinner_item);
        view.setRegionSpinnerAdapter(adapter);
    }


}
