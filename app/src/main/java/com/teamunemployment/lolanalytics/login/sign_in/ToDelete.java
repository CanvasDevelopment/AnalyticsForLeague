package com.teamunemployment.lolanalytics.login.sign_in;

import android.content.Context;


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
//
//    @Provides
//    LoginInteractor provideLoginModel(LoginRemoteRepo loginRemoteRepo, RealmExecutor realmExecutor) {
//        return  new LoginInteractor(loginRemoteRepo, realmExecutor);
//    }

    @Provides
    LoginPresenter provideLoginPresenter(ArrayAdapterFactory arrayAdapterFactory, LoginInteractor loginInteractor) {
        return new LoginPresenter(arrayAdapterFactory, loginInteractor);
    }
}
