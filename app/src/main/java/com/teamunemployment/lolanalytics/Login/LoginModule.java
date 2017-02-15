package com.teamunemployment.lolanalytics.Login;

import android.content.Context;

import com.teamunemployment.lolanalytics.Data.RESTApiExecutor;
import com.teamunemployment.lolanalytics.Data.RealmExecutor;

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
    LoginBasePresenter provideLoginPresenter(ArrayAdapterFactory arrayAdapterFactory, LoginModel loginModel) {
        return new LoginBasePresenter(arrayAdapterFactory, loginModel);
    }
}
