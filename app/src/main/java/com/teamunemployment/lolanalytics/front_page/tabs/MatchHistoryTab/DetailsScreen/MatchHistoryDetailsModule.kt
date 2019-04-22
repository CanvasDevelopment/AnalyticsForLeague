package com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.DetailsScreen

import android.view.LayoutInflater
import com.teamunemployment.lolanalytics.data.model.SummonerRapidAccessObject
import org.koin.android.module.AndroidModule

/**
 * Created by Josiah Kendall
 */
class MatchHistoryDetailsModule : AndroidModule() {
    override fun context() = applicationContext {

        // TODO
        context(name = "MatchHistoryDetailsModule") {
            provide { DetailsPresenter(get(), get(), get(), get()) }
            provide { DetailsInteractor(get(), get(), get<SummonerRapidAccessObject>(), get(), get(), get()) }
            provide { LayoutInflater.from(get()) }
            provide { ViewProducer(get()) }

        }
    }
}