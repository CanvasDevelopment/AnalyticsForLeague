package com.teamunemployment.lolanalytics.Login;

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
    LoginPresenter provideLoginPresenter() {
        return new LoginPresenter();
    }
}
