package com.teamunemployment.lolanalytics;

import android.app.Application;

import com.teamunemployment.lolanalytics.DependencyInjection.AppComponent;
import com.teamunemployment.lolanalytics.DependencyInjection.AppModule;
import com.teamunemployment.lolanalytics.DependencyInjection.DaggerAppComponent;
import com.teamunemployment.lolanalytics.Login.LoginModule;
import com.teamunemployment.lolanalytics.FrontPage.StatsComparisonTab.StatisticsTabModule;

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
                .statisticsTabModule(new StatisticsTabModule())
                .build();
    }

    public AppComponent getNetComponent() {
        return appComponent;
    }


}
