package com.teamunemployment.lolanalytics.front_page.tabs.StatTab

import com.teamunemployment.lolanalytics.Utils.getHeadToHeadStats
import com.teamunemployment.lolanalytics.Utils.getStatList
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.Model.StatSummary
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import com.teamunemployment.lolanalytics.mock.MockAnalysisServiceResponses
import com.teamunemployment.lolanalytics.mock.MockHttpResponseInterceptor
import org.junit.Test
import retrofit2.Response

/**
 * Created by Josiah Kendall
 */
class AnalyseInteractorTests {

    val mockAnalysisResponses = MockAnalysisServiceResponses()

    @Test
    fun `Make sure that we can process the stat list`() {

    }


    @Test
    fun `Make sure that we can convert the mock match list id array`() {
        // getMatchIds array string
        val stringResponse = mockAnalysisResponses.getStatIds(-1)
        // make intercepter
        val interceptor = MockHttpResponseInterceptor(stringResponse, 200)
        // build retrofit mock
        val retrofitMock = RetrofitFactory().produceMockRetrofitInterface(AnalysisService::class.java, interceptor)
        // getMatchIds response
        val call = retrofitMock.getStatList()

        val response : Response<Result<ArrayList<StatSummary>>> =  call.execute()

        val result : Result<ArrayList<StatSummary>> = response.getStatList()
        assert(result.data.size == 3)
        assert(result.data[0].type == 2)
        assert(result.data[0].cardUrl == "testurl")
        assert(result.data[0].detailUrl == "detailsurl")
        assert(result.data[1].type == 1)
    }

    @Test
    fun `Make sure that we can convert the mock full stat card response`() {
// getMatchIds array string
        val stringResponse = mockAnalysisResponses.getFullStatCard()
        // make intercepter
        val interceptor = MockHttpResponseInterceptor(stringResponse, 200)
        // build retrofit mock
        val retrofitMock = RetrofitFactory().produceMockRetrofitInterface(AnalysisService::class.java, interceptor)
        // getMatchIds response
        val call = retrofitMock.getFullCardStat("")

        val response : Response<Result<ArrayList<HeadToHeadStat>>> =  call.execute()

        val result : Result<ArrayList<HeadToHeadStat>> = response.getHeadToHeadStats()
        assert(result.data.size == 3)
        assert(result.data[0].enemyStatValue == 51.3f)
        assert(result.data[0].heroStatValue == 48.7f)

        assert(result.data[1].enemyStatValue == 52.3f)
        assert(result.data[1].heroStatValue == 47.7f)

        assert(result.data[2].enemyStatValue == 53.3f)
        assert(result.data[2].heroStatValue == 46.7f)
    }

    @Test
    fun `Make sure that we can convert the mock half stat card response`() {
// getMatchIds array string
        val stringResponse = mockAnalysisResponses.getHalfStatCard()
        // make intercepter
        val interceptor = MockHttpResponseInterceptor(stringResponse, 200)
        // build retrofit mock
        val retrofitMock = RetrofitFactory().produceMockRetrofitInterface(AnalysisService::class.java, interceptor)
        // getMatchIds response
        val call = retrofitMock.getFullCardStat("")

        val response : Response<Result<ArrayList<HeadToHeadStat>>> =  call.execute()

        val result : Result<ArrayList<HeadToHeadStat>> = response.getHeadToHeadStats()
        assert(result.data.size == 1)
        assert(result.data[0].enemyStatValue == 51.3f)
        assert(result.data[0].heroStatValue == 48.7f)

    }

}