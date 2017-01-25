package com.teamunemployment.lolanalytics.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.teamunemployment.lolanalytics.FrontPage.BaseActivityView;
import com.teamunemployment.lolanalytics.App;
import com.teamunemployment.lolanalytics.R;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Josiah Kendall
 *
 * Simple login screen for new users.
 */
public class LoginView extends AppCompatActivity implements LoginContract.LoginView {

    @Inject
    public LoginPresenter loginPresenter;

    @Bind(R.id.regions_spinner) AppCompatSpinner regionSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        ((App) getApplication()).getNetComponent().InjectView(this);
        ButterKnife.bind(this);
        loginPresenter.setView(this);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.login_without_credentials)
    public void loginWithoutCredentials() {
        loginPresenter.LoginTestUser();
    }

    @OnClick(R.id.login_with_credentials)
    public void loginWithCredentials() {
        showMessage("Error: Analysis.gg is not available in your region yet.");
    }

    @Override
    public void launchHomeActivity() {
        Intent mainIntent = new Intent(this, BaseActivityView.class);
        startActivity(mainIntent);
    }

    @Override
    public void setRegionSpinnerAdapter(ArrayAdapter<CharSequence> adapter) {
        regionSpinner.setAdapter(adapter);
    }
}
