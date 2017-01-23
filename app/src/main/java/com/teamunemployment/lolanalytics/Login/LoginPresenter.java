package com.teamunemployment.lolanalytics.Login;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.teamunemployment.lolanalytics.R;

import javax.inject.Inject;

/**
 * @author Josiah Kendall
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.LoginView view;

    private ArrayAdapterFactory arrayAdapterFactory;

    @Inject
    public LoginPresenter(ArrayAdapterFactory arrayAdapterFactory) {
        this.arrayAdapterFactory = arrayAdapterFactory;
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
    public void LoginTestUser() {
        view.launchHomeActivity();
    }

    @Override
    public void setView(LoginContract.LoginView loginView) {
        this.view = loginView;
        start();
    }

    private void setRegionAdapter() {
        ArrayAdapter<CharSequence> adapter = arrayAdapterFactory.getArrayAdapter(R.array.regions_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        view.setRegionSpinnerAdapter(adapter);
    }


}
