package com.teamunemployment.lolanalytics.FrontPage.StatsComparisonTab;

import android.app.Application;
import android.content.Context;

import com.teamunemployment.lolanalytics.Data.RealmExecutor;
import com.teamunemployment.lolanalytics.StatsComparisonTab.Model.BarChartCardModel;
import com.teamunemployment.lolanalytics.StatsComparisonTab.Model.BarChartFactory;
import com.teamunemployment.lolanalytics.Data.RESTApiExecutor;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Josiah Kendall
 *
 * Provide the dependencies for our head to head stats tabs.
 */
@Module
public class StatisticsTabModule {

    @Provides
    Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    BarChartFactory provideBarChartFactory() {
        return new BarChartFactory();
    }

    @Provides
    RESTApiExecutor provideApi() {
        // Currently using this for testing - will be removed in the future.
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://lolanalyticsv3.appspot.com/_ah/api/myApi/v1/")
                .build();

        RESTApiExecutor RESTApiExecutor = retrofit.create(RESTApiExecutor.class);
        return RESTApiExecutor;
    }

    @Provides
    RealmExecutor provideRealmInterface(Context context) {
        RealmExecutor realmExecutor = new RealmExecutor(context);
        return realmExecutor;
    }

    @Provides
    TabModel provideJungleModel(RESTApiExecutor RESTApiExecutor, RealmExecutor realmExecutor, Context context) {
        return new TabModel(RESTApiExecutor, realmExecutor, context);
    }

    @Provides
    TabPresenter provideBasePresenter(TabModel tabModel, TabRecyclerAdapter tabRecyclerAdapter) {
        return new TabPresenter(tabModel, tabRecyclerAdapter);
    }

    @Provides
    BarChartCardModel provideBarChartCardModel(BarChartFactory barChartFactory) {
        return new BarChartCardModel(barChartFactory);
    }

    @Provides
    TabRecyclerAdapter provideBaseRecyclerAdapter(BarChartCardModel barChartCardModel) {
        return new TabRecyclerAdapter(barChartCardModel);
    }
}
