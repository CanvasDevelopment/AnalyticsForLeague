package com.teamunemployment.lolanalytics.DependencyInjection;

import android.content.Context;

import com.teamunemployment.lolanalytics.Jungle.Model.BarChartFactory;
import com.teamunemployment.lolanalytics.Jungle.Model.JungleModel;
import com.teamunemployment.lolanalytics.Jungle.PresentationLayer.JunglePresenter;
import com.teamunemployment.lolanalytics.RESTService.Api;
import com.teamunemployment.lolanalytics.mock.MockHttpClient;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Josiah Kendall
 */

@Module
public class ComponentModule {

    @Provides
    BarChartFactory provideBarChartFactory() {
        return new BarChartFactory();
    }

    @Provides
    Api provideApi() {

        Retrofit retrofit = new Retrofit.Builder()
                // .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http:tete.com/irrelevant/foo/")
                .build();

        Api api = retrofit.create(Api.class);
        return api;
    }

    @Provides
    JungleModel provideJungleModel(Api api) {
        return new JungleModel(api);
    }

    @Provides
    JunglePresenter provideJunglePresenter(JungleModel jungleModel) {
        return new JunglePresenter(jungleModel);
    }
}
