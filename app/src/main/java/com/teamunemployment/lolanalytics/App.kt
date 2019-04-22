package com.teamunemployment.lolanalytics

import android.app.Application
import android.arch.persistence.room.Room
import com.teamunemployment.lolanalytics.data.room.AppDatabase
import com.teamunemployment.lolanalytics.data.room.champ.ChampModule
import com.teamunemployment.lolanalytics.data.source.DatasourceModule
import com.teamunemployment.lolanalytics.di.BaseModule

import com.teamunemployment.lolanalytics.front_page.BaseActivityModule
import com.teamunemployment.lolanalytics.front_page.champ_menu.ChampMenuModule
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.DetailsScreen.MatchHistoryDetailsModule
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.MatchHistoryModule
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen.StatDetailsModule
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.di.StatModule
import com.teamunemployment.lolanalytics.login_page.di.SignInModule
import com.teamunemployment.lolanalytics.io.di.IoModule
import com.teamunemployment.lolanalytics.login_page.di.OnboardingModule
import org.koin.android.ext.android.startAndroidContext
import org.koin.android.module.AndroidModule
import timber.log.Timber

/**
 * @author Josiah Kendall.
 *
 * Application class. For objects that should only exist once per application lifespan. i.e Dagger
 */

class App : Application() {


    companion object {
        lateinit var database : AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Initialise our room database
        database = Room.databaseBuilder(
                    this,
                    AppDatabase::class.java,
                    "analytics_for_league.db"
        )
        .build()

        // initialise koin
        startAndroidContext(this, modules())
    }


    private fun modules() : List<AndroidModule> {
        val list = ArrayList<AndroidModule>()
        list.add(SignInModule())
        list.add(IoModule())
        list.add(ChampModule())
        list.add(OnboardingModule())
        list.add(BaseActivityModule())
        list.add(ChampMenuModule())
        list.add(StatModule())
        list.add(MatchHistoryModule())
        list.add(BaseModule())
        list.add(MatchHistoryDetailsModule())
        list.add(DatasourceModule())
        list.add(StatDetailsModule())
        return list
    }


}
