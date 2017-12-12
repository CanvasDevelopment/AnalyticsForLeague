package com.teamunemployment.lolanalytics.io.networking

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Josiah Kendall
 */

class RetrofitFactory {



    /**
     * Produce a retrofit interface.
     * @param type      The type of interface that we want to produce
     * @param baseUrl   The
     * @return a retrofit interface of the type specified
     */
    fun <T> produceRetrofitInterface(type : Class<T>, baseUrl : String) : T {
        val retrofit : Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl) // "https://lolanalyticsv3.appspot.com/_ah/api/myApi/v1/"
                .build()

        return retrofit.create(type)
    }
}