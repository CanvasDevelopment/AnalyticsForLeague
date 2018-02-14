package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen

import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.Utils.getMatchPerformanceDetails
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.data.model.SummonerRapidAccessObject
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.MatchHistoryService
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import com.teamunemployment.lolanalytics.mock.MockHttpResponseInterceptor
import com.teamunemployment.lolanalytics.mock.MockMatchHistoryServiceResponses
import retrofit2.Call
import retrofit2.Response

/**
 * @author Josiah Kendall
 */
class DetailsInteractor(private val retrofitFactory: RetrofitFactory,
                        private val network: Network,
                        private val summonerRapidAccessObject: SummonerRapidAccessObject) {

    private val mockResponses = MockMatchHistoryServiceResponses()

    private val mockInterceptor = MockHttpResponseInterceptor("200", 200)

    /**
     * Fetch the all the data that we need for the details page.
     *
     * @param matchId       The id of the match that we are fetching details for.
     * @param summonerId    The hero summonerId.
     * @param role          The role of the hero and his opponent
     * @param presenter     Callback to this when the data is loaded.
     */
    fun fetchDetailsForMatch(matchId: Long, summonerId: Long, role: String, presenter : DetailsPresenter) {
        async {
            val url = network.getUrl(summonerRapidAccessObject.getRegion())
            val matchHistoryService = retrofitFactory.produceMockRetrofitInterface(MatchHistoryService::class.java, mockInterceptor)
            mockInterceptor.content = mockResponses.getMatchDetails()
            val call: Call<Result<MatchPerformanceDetails>> = matchHistoryService.fetchMatchDetails(matchId,summonerId)
            val response: Response<Result<MatchPerformanceDetails>> = await { call.execute() }

            val result: Result<MatchPerformanceDetails> = response.getMatchPerformanceDetails()
            val code = result.resultCode
            presenter.handleDetailsResponse(code, result.data)
        }
    }

    fun calculatePerformanceForUser(performanceDetails: MatchPerformanceDetails) {

    }

}