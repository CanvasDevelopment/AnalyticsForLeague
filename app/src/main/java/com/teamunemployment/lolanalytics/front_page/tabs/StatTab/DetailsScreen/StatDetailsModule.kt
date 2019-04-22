package com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen

import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen.tabs.StatDetailsTabInteractor
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen.tabs.StatDetailsTabPresenter
import org.koin.android.module.AndroidModule


/**
 * Created by Josiah Kendall
 */
class StatDetailsModule : AndroidModule(){
    override fun context() = applicationContext {
        context(name = "StatDetailsModule") {
            provide { DetailsInteractor(get(), get(), get()) }
            provide { DetailsPresenter(get()) }
            provide { StatDetailsTabInteractor(get(), get())}
            provide { StatDetailsTabPresenter(get(), get()) }
        }
    }

}