package com.teamunemployment.lolanalytics.di

import android.preference.PreferenceManager
import com.teamunemployment.lolanalytics.Utils.RegionHelper
import com.teamunemployment.lolanalytics.data.model.SummonerRapidAccessObject
import org.koin.android.module.AndroidModule

/**
 * @author Josiah Kendall
 */
class BaseModule : AndroidModule() {
    override fun context()= applicationContext {
        context(name = "BaseModule") {
            provide { PreferenceManager.getDefaultSharedPreferences(context) }
            provide { RegionHelper(get()) }
            provide { SummonerRapidAccessObject(get()) }
        }
    }
}


