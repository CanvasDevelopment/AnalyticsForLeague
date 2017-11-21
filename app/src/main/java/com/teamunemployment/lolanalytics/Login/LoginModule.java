package com.teamunemployment.lolanalytics.login;

import android.content.Context;

import com.teamunemployment.lolanalytics.io.RESTApiExecutor;
import com.teamunemployment.lolanalytics.io.RealmExecutor;

import dagger.Module;
import dagger.Provides;

/**
 * @author Josiah Kendall
 *
 * Provides components for the login page architecture.
 */

@Module
public class LoginModule {

    @Provides
    ArrayAdapterFactory provideArrayAdapterFactory(Context context) {
        return new ArrayAdapterFactory(context);
    }

    @Provides
    LoginModel provideLoginModel(RESTApiExecutor restApiExecutor, RealmExecutor realmExecutor) {
        return  new LoginModel(restApiExecutor, realmExecutor);
    }

    @Provides
    LoginPresenter provideLoginPresenter(ArrayAdapterFactory arrayAdapterFactory, LoginModel loginModel) {
        return new LoginPresenter(arrayAdapterFactory, loginModel);
    }
}
