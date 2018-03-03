package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.di

import com.teamunemployment.lolanalytics.Utils.RoleUtils
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.recycler.AnalyseAdapter
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.AnalyseInteractor
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.AnalysePresenter
import org.koin.android.module.AndroidModule

/**
 * @author Josiah Kendall
 */
class StatModule : AndroidModule() {
    override fun context() = applicationContext {

        context(name = "StatModule") {
            provide { AnalysePresenter(get(),get(), get()) }
            provide { RoleUtils() }
            provide { AnalyseInteractor(get(), get()) }
            provide { AnalyseAdapter() }
        }
    }
}