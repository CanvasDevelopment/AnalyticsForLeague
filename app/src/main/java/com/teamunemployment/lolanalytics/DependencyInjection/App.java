package com.teamunemployment.lolanalytics.DependencyInjection;

import android.app.Application;

/**
 * @author Josiah Kendall.
 *
 * Application class. For objects that should only exist once per application lifespan. i.e Dagger
 */

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialise dagger2.
        appComponent = DaggerAppComponent.builder()
                // Modules go here
                .appModule(new AppModule(this)) //
                .componentModule(new ComponentModule())
                .build();
    }

    public AppComponent getNetComponent() {
        return appComponent;
    }
}
