package com.teamunemployment.lolanalytics.io.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.io.RESTApiExecutor
import com.teamunemployment.lolanalytics.io.RealmExecutor
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import org.koin.android.module.AndroidModule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Josiah Kendall
 */
class IoModule : AndroidModule() {
    override fun context()= applicationContext {
        context(name = "IoModule") {
            provide {
                val retrofit : Retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .baseUrl("https://lolanalyticsv3.appspot.com/_ah/api/myApi/v1/")
                        .build()

                retrofit.create(RESTApiExecutor::class.java)
            }

            provide { RealmExecutor(applicationContext) }
            provide { RetrofitFactory() }

        }
    }

}