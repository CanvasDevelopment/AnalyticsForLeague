package com.teamunemployment.lolanalytics.login.sign_in;

import android.content.Context;

import com.teamunemployment.lolanalytics.io.RealmExecutor;

import dagger.Module;
import dagger.Provides;

/**
 * @author Josiah Kendall
 *
 * Provides components for the syncAUser page architecture.
 */
//
@Module
public class ToDelete {

    @Provides
    ArrayAdapterFactory provideArrayAdapterFactory(Context context) {
        return new ArrayAdapterFactory(context);
    }

    @Provides
    LoginModel provideLoginModel(LoginRemoteRepo loginRemoteRepo, RealmExecutor realmExecutor) {
        return  new LoginModel(loginRemoteRepo, realmExecutor);
    }

    @Provides
    LoginPresenter provideLoginPresenter(ArrayAdapterFactory arrayAdapterFactory, LoginModel loginModel) {
        return new LoginPresenter(arrayAdapterFactory, loginModel);
    }
}
