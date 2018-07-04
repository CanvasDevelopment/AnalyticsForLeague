package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen

import android.view.LayoutInflater
import com.teamunemployment.lolanalytics.data.model.SummonerRapidAccessObject
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.MatchHistoryInteractor
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.MatchHistoryPresenter
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Model.BarChartModel
import org.koin.android.module.AndroidModule

/**
 * Created by Josiah Kendall
 */
class MatchHistoryDetailsModule : AndroidModule() {
    override fun context() = applicationContext {

        // TODO
        context(name = "MatchHistoryDetailsModule") {
            provide { DetailsPresenter(get(), get(), get()) }
            provide { DetailsInteractor(get(), get(), get<SummonerRapidAccessObject>()) }
            provide { LayoutInflater.from(get()) }
            provide { ViewProducer(get()) }

        }
    }
}