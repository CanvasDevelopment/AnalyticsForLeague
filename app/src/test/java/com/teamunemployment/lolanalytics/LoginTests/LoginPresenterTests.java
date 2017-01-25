package com.teamunemployment.lolanalytics.LoginTests;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.teamunemployment.lolanalytics.Login.ArrayAdapterFactory;
import com.teamunemployment.lolanalytics.Login.LoginModel;
import com.teamunemployment.lolanalytics.Login.LoginPresenter;
import com.teamunemployment.lolanalytics.Login.LoginView;

import org.junit.Test;


import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Josiah Kendall
 */

public class LoginPresenterTests {

    @Test
    public void TestThatWeSetRegionSpinner() {
        ArrayAdapterFactory arrayAdapterFactory = mock(ArrayAdapterFactory.class);
        ArrayAdapter<CharSequence> adapter = mock(ArrayAdapter.class);
        when(arrayAdapterFactory.getArrayAdapter(anyInt(), anyInt())).thenReturn(adapter);
        LoginModel loginModel = mock(LoginModel.class);
        LoginPresenter loginPresenter = new LoginPresenter(arrayAdapterFactory, loginModel);
        LoginView loginView = mock(LoginView.class);
        loginPresenter.setView(loginView);

        verify(loginView, times(1)).setRegionSpinnerAdapter(any(ArrayAdapter.class));
    }

    @Test
    public void TestThatWeCanNofityUserOfFailedLogin() {

    }

    @Test
    public void TestTHatWeCanLaunchActivityIfSuccessfulLogin() {

    }

    @Test
    public void TEstThatWeSaveUserDetailsGivenCorrectLogin() {

    }


}