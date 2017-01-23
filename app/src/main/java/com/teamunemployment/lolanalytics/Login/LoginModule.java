package com.teamunemployment.lolanalytics.Login;

import android.app.Application;
import android.content.Context;

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
    LoginPresenter provideLoginPresenter(ArrayAdapterFactory arrayAdapterFactory) {
        return new LoginPresenter(arrayAdapterFactory);
    }
}
