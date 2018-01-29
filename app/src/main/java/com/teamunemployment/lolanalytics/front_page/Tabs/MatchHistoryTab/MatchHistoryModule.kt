package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab

import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.AnalyseAdapter
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.AnalyseInteractor
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.AnalysePresenter
import org.koin.android.module.AndroidModule

/**
 * @author Josiah Kendall
 */
class MatchHistoryModule : AndroidModule() {
    override fun context() = applicationContext {

        // TODO
        context(name = "MatchHistoryModule") {
            provide { MatchHistoryPresenter(get(),get()) }
            provide { MatchHistoryInteractor(get(), get()) }
            provide { AnalyseAdapter() }
        }
    }
}