package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.di

import com.teamunemployment.lolanalytics.Utils.RoleUtils
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.recycler.AnalyseAdapter
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.AnalyseInteractor
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.AnalysePresenter
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.DetailsScreen.DetailsInteractor
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.DetailsScreen.DetailsPresenter
import org.koin.android.module.AndroidModule

/**
 * @author Josiah Kendall
 */
class StatModule : AndroidModule() {
    override fun context() = applicationContext {

        context(name = "StatModule") {
            provide { AnalysePresenter(get(),get(), get(), get(), get()) }
            provide { RoleUtils() }
            provide { AnalyseInteractor(get(), get()) }
            provide { AnalyseAdapter(get()) }
            provide { DetailsPresenter(get()) }
            provide { DetailsInteractor(get(), get(), get())}
        }
    }
}