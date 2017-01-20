package com.teamunemployment.lolanalytics.DependencyInjection;

import android.app.Application;
import android.content.Context;

import com.teamunemployment.lolanalytics.Base.BaseRecyclerAdapter;
import com.teamunemployment.lolanalytics.Base.RealmExecutor;
import com.teamunemployment.lolanalytics.Jungle.Model.BarChartCardModel;
import com.teamunemployment.lolanalytics.Jungle.Model.BarChartFactory;
import com.teamunemployment.lolanalytics.Base.BaseModel;
import com.teamunemployment.lolanalytics.Base.BasePresenter;
import com.teamunemployment.lolanalytics.RESTService.RESTApiExecutor;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Josiah Kendall
 *
 * Provide the dependencies.
 */
@Module
public class ComponentModule {

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
    BaseModel provideJungleModel(RESTApiExecutor RESTApiExecutor, RealmExecutor realmExecutor, Context context) {
        return new BaseModel(RESTApiExecutor, realmExecutor, context);
    }

    @Provides
    BasePresenter provideBasePresenter(BaseModel baseModel, BaseRecyclerAdapter baseRecyclerAdapter) {
        return new BasePresenter(baseModel, baseRecyclerAdapter);
    }

    @Provides
    BarChartCardModel provideBarChartCardModel(BarChartFactory barChartFactory) {
        return new BarChartCardModel(barChartFactory);
    }

    @Provides
    BaseRecyclerAdapter provideBaseRecyclerAdapter(BarChartCardModel barChartCardModel) {
        return new BaseRecyclerAdapter(barChartCardModel);
    }
}
