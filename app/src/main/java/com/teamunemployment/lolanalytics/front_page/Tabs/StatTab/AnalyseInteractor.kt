package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab

import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.Utils.getStatList
import com.teamunemployment.lolanalytics.data.model.DoubleWrapper
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.BaseActivityService
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.AnalysisData
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatList
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.FullStatCard
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatSummary
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.recycler.StatCardContract
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.recycler.StatCardView
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import com.teamunemployment.lolanalytics.mock.MockAnalysisServiceResponses
import com.teamunemployment.lolanalytics.mock.MockHttpResponseInterceptor
import retrofit2.Call
import retrofit2.Response
import ru.gildor.coroutines.retrofit.await

import java.util.ArrayList

/**
 * Created by Josiah Kendall
 */

class AnalyseInteractor(private val retrofitFactory: RetrofitFactory,
                        private val network: Network) {

    private lateinit var playerAnalysisRemoteRepo: PlayerAnalysisRemoteRepo

    fun loadStatTypes(role: Int, summonerId: Long, presenter: AnalysePresenter) {
        val url = network.getUrl(summonerId)
        playerAnalysisRemoteRepo = retrofitFactory.produceRetrofitInterface(PlayerAnalysisRemoteRepo::class.java, url)
        async {
            val result = playerAnalysisRemoteRepo.fetchStatList(role).await()
            result.cache()
//            presenter.setStatList(result.data)
        }
    }

    fun loadIndividualStat(url: String, viewHolder : StatCardView, presenter : AnalysePresenter) {
        val mockResponses = MockAnalysisServiceResponses()
        val mockInterceptor = MockHttpResponseInterceptor(mockResponses.getFullStatCard(), 200)
        async {
            val analysisService = retrofitFactory.produceMockRetrofitInterface(AnalysisService::class.java, mockInterceptor)
            val result : Result<ArrayList<HeadToHeadStat>> = analysisService.getFullCardStat(url).await()
            result.cache()
            presenter.handleCardResult(result.data, viewHolder)
        }
    }

    /**
     * Cache our data that we have fetched.
     */
    private suspend fun <T> Result<T>.cache(): Boolean {

        if (resultCode == 200) {
            cacheData(data)
            TODO("Implement caching for player analysis interactor")
            return true
        }
        return false
    }

    private fun <T> cacheData(data: T): Boolean {
        when (data) {
            is StatList -> {
                // do stat cache
                return true
            }
            is FullStatCard -> {
                return true
            }
        }

        return false
    }

    fun loadStatList(role: Int, champId: Int, presenter: AnalysePresenter, region: String) {
        val datas = ArrayList<AnalysisData>()
        val analysisData = AnalysisData()
//        presenter.setStatList(removeMeAfterMocking())
    }

    fun loadStatList(role: Int, presenter: AnalysePresenter, region: String, summonerId: Long) {
        val mockResponses = MockAnalysisServiceResponses()
        val mockInterceptor = MockHttpResponseInterceptor(mockResponses.getStatIds(-1), 200)

        async {
            val url = network.getUrl(region)
            val analysisService = retrofitFactory.produceMockRetrofitInterface(AnalysisService::class.java, mockInterceptor)
            mockInterceptor.content = mockResponses.getStatIds(-1)
            val call: Call<Result<ArrayList<StatSummary>>> = analysisService.getStatList()
            val response : Response<Result<ArrayList<StatSummary>>> = await { call.execute() }

            val result : Result<ArrayList<StatSummary>> = response.getStatList()
            val code = result.resultCode // do something here?
            presenter.setStatList(result.data)
//            presenter.onMatchListLoadedSuccessfully(result.data)
        }


        // send request
        // send request to
//        presenter.setStatList(removeMeAfterMocking())
    }
}
