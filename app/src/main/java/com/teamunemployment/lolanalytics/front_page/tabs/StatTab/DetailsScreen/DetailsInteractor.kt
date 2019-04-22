package com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen

import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.data.model.SummonerRapidAccessObject
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.AnalysisService
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen.model.StatDetailsDataModel
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import com.teamunemployment.lolanalytics.mock.MockAnalysisServiceResponses
import com.teamunemployment.lolanalytics.mock.MockHttpResponseInterceptor
import ru.gildor.coroutines.retrofit.await

/**
 * Created by Josiah Kendall
 */
class DetailsInteractor(private val retrofitFactory: RetrofitFactory,
                        private val network: Network,
                        private val summonerRapidAccessObject: SummonerRapidAccessObject) {

    fun fetchDetails(url: String, detailsPresenter: DetailsPresenter) {
        val mockResponses = MockAnalysisServiceResponses()
        val mockInterceptor = MockHttpResponseInterceptor(mockResponses.getStatDetails(), 200)
        async {
            val analysisService = retrofitFactory.produceMockRetrofitInterface(AnalysisService::class.java, mockInterceptor)
            val result : Result<StatDetailsDataModel> = await{analysisService.getStatDetails(url).execute().body()!!}

            // todo
//            result.cache()
            detailsPresenter.onDetailsLoaded(result.data)
        }.onError {
            detailsPresenter.showMessage("Offline - check you internet connection")
        }
    }

}