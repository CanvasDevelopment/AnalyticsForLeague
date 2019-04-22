package com.teamunemployment.lolanalytics.io.networking

import com.teamunemployment.lolanalytics.mock.MockHttpResponseInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
        val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client : OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        val retrofit : Retrofit = Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl) // "https://lolanalyticsv3.appspot.com/_ah/api/myApi/v1/"
                .build()

        return retrofit.create(type)
    }

    /**
     * Produce a mock retrofit interface. Use the [mockHttpResponseInterceptor] param to set up a custom
     * response, so that we can simulate network response / result without needing the server to be
     * running.
     * @param type                          The type of interface that we want to produce.
     * @param mockHttpResponseInterceptor   The interceptor that we use to simulate network conditions.
     * @return A retrofit interface of the [type] specified.
     */
    fun <T> produceMockRetrofitInterface(type : Class<T>, mockHttpResponseInterceptor: MockHttpResponseInterceptor) : T {
        val retrofit : Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://test.com/")
                .client(
                        // Create a client with a mock interceptor, so that we always return the data that we need
                        OkHttpClient
                        .Builder()
                        .addInterceptor(mockHttpResponseInterceptor)
                        .build()
                        )
                .build()
        return retrofit.create(type)
    }
}