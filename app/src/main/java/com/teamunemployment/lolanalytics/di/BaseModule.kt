package com.teamunemployment.lolanalytics.di

import com.teamunemployment.lolanalytics.data.model.SummonerRapidAccessObject
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import org.koin.android.module.AndroidModule

/**
 * @author Josiah Kendall
 */
class BaseModule : AndroidModule() {
    override fun context()= applicationContext {
        context(name = "BaseModule") {
            provide { SummonerRapidAccessObject() }
        }
    }

}