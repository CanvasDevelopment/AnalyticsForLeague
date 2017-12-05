package com.teamunemployment.lolanalytics

import android.app.Application

import com.teamunemployment.lolanalytics.di.AppComponent
import com.teamunemployment.lolanalytics.login.di.SignInModule
import com.teamunemployment.lolanalytics.io.di.IoModule
import org.koin.android.ext.android.startAndroidContext
import org.koin.android.module.AndroidModule

/**
 * @author Josiah Kendall.
 *
 * Application class. For objects that should only exist once per application lifespan. i.e Dagger
 */

class App : Application() {

    val netComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        startAndroidContext(this, modules())
        // Initialise dagger2.
        //        appComponent = DaggerAppComponent.builder()
        //                // Modules go here
        //                .appModule(new AppModule(this)) //
        //                .statisticsTabModule(new StatisticsTabModule())
        //                .loginModule(new ToDelete())
        //                .build();

    }

    private fun modules() : List<AndroidModule> {
        val list = ArrayList<AndroidModule>()
        list.add(SignInModule())
        list.add(IoModule())
        return list
    }


}
