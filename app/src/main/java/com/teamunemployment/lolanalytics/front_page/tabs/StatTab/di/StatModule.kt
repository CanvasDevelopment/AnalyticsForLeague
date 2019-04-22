package com.teamunemployment.lolanalytics.front_page.tabs.StatTab.di

import com.teamunemployment.lolanalytics.Utils.Role
import com.teamunemployment.lolanalytics.Utils.RoleUtils
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.recycler.AnalyseAdapter
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.AnalyseInteractor
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.AnalysePresenter
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen.DetailsInteractor
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen.DetailsPresenter
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
        }
    }
}