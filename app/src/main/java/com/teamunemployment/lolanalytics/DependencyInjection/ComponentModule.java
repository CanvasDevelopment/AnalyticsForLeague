package com.teamunemployment.lolanalytics.DependencyInjection;

import android.app.Application;
import android.content.Context;

import com.teamunemployment.lolanalytics.Jungle.Model.BarChartFactory;
import com.teamunemployment.lolanalytics.Base.BaseModel;
import com.teamunemployment.lolanalytics.Base.BasePresenter;
import com.teamunemployment.lolanalytics.RESTService.Api;
import com.teamunemployment.lolanalytics.Mock.MockHttpClient;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Josiah Kendall
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
    Api provideApi() {
        // Currently using this for testing - will be removed in the future.
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://lolanalyticsv3.appspot.com/_ah/api/myApi/v1/")
            //    .baseUrl("http://192.168.20.17:8080/_ah/api/myApi/v1/")
              //  .client(okHttpClient) // For testing purposes.
                .build();

        Api api = retrofit.create(Api.class);
        return api;
    }

    @Provides
    BaseModel provideJungleModel(Api api) {
        return new BaseModel(api);
    }

    @Provides
    BasePresenter provideJunglePresenter(BaseModel baseModel) {
        return new BasePresenter(baseModel);
    }
}
