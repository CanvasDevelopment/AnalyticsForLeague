package com.teamunemployment.lolanalytics.io.di

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
            provide { RetrofitFactory() }
        }
    }

}