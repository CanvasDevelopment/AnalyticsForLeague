package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.DetailsScreen

import org.koin.android.module.AndroidModule


/**
 * Created by Josiah Kendall
 */
class StatDetailsModule : AndroidModule(){
    override fun context() = applicationContext {
        context(name = "StatDetailsModule") {
            provide { DetailsInteractor(get(), get(), get()) }
            provide { DetailsPresenter(get()) }
        }
    }

}