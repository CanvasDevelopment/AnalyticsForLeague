package com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab

import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.BarChartModel
import org.koin.android.module.AndroidModule

/**
 * @author Josiah Kendall
 */
class MatchHistoryModule : AndroidModule() {
    override fun context() = applicationContext {

        // TODO
        context(name = "MatchHistoryModule") {
            provide { MatchHistoryPresenter(get(), get(), get(), get()) }
            provide { MatchHistoryInteractor(get(), get(), get(), get(), get()) }
            provide { BarChartModel() }
            provide { MatchHistoryRecycleViewAdapter()}
        }
    }
}