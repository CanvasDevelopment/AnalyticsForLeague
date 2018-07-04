package com.teamunemployment.lolanalytics

import android.app.Application
import android.arch.persistence.room.Room
import com.teamunemployment.lolanalytics.data.room.Database
import com.teamunemployment.lolanalytics.di.BaseModule

import com.teamunemployment.lolanalytics.front_page.BaseActivityModule
import com.teamunemployment.lolanalytics.front_page.Search.SearchModule
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen.MatchHistoryDetailsModule
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.MatchHistoryModule
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.di.StatModule
import com.teamunemployment.lolanalytics.login.di.SignInModule
import com.teamunemployment.lolanalytics.io.di.IoModule
import com.teamunemployment.lolanalytics.login.di.OnboardingModule
import org.koin.android.ext.android.startAndroidContext
import org.koin.android.module.AndroidModule

/**
 * @author Josiah Kendall.
 *
 * Application class. For objects that should only exist once per application lifespan. i.e Dagger
 */

class App : Application() {


    companion object {
        lateinit var database : Database
    }

    override fun onCreate() {
        super.onCreate()

        // Initialise our room database
        database = Room.databaseBuilder(
                    this,
                    Database::class.java,
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
        list.add(OnboardingModule())
        list.add(BaseActivityModule())
        list.add(SearchModule())
        list.add(StatModule())
        list.add(MatchHistoryModule())
        list.add(BaseModule())
        list.add(MatchHistoryDetailsModule())
        return list
    }


}
