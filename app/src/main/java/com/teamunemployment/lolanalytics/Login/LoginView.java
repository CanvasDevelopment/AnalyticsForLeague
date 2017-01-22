package com.teamunemployment.lolanalytics.Login;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.teamunemployment.lolanalytics.Base;
import com.teamunemployment.lolanalytics.DependencyInjection.App;
import com.teamunemployment.lolanalytics.R;

import javax.inject.Inject;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter();
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


    @Override
    public void launchHomeActivity() {
        Intent mainIntent = new Intent(this, Base.class);
        startActivity(mainIntent);
    }
}
