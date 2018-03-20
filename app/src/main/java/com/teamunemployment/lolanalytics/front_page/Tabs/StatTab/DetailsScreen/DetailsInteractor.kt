package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.DetailsScreen

import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.model.SummonerRapidAccessObject
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory

/**
 * Created by Josiah Kendall
 */
class DetailsInteractor(private val retrofitFactory: RetrofitFactory,
                        private val network: Network,
                        private val summonerRapidAccessObject: SummonerRapidAccessObject) {
}