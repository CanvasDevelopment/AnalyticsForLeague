package com.teamunemployment.lolanalytics.io.di;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.teamunemployment.lolanalytics.io.RESTApiExecutor;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Josiah Kendall
 */

@Module
public class IoModuleDagger2 {

    @Provides
    public RESTApiExecutor provideRestApiExecuor() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://lolanalyticsv3.appspot.com/_ah/api/myApi/v1/")
                .build();

        return retrofit.create(RESTApiExecutor.class);
    }
}
