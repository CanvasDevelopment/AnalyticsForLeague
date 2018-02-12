package com.teamunemployment.lolanalytics.DataSourceTests

import com.teamunemployment.lolanalytics.Utils.getMatchHistoryCardData
import com.teamunemployment.lolanalytics.Utils.getMatchIds
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.MatchHistoryService
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Model.MatchHistoryCardData
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import com.teamunemployment.lolanalytics.mock.MockHttpResponseInterceptor
import com.teamunemployment.lolanalytics.mock.MockMatchHistoryServiceResponses
import org.junit.Test

import retrofit2.Response

/**
 * @author Josiah Kendall
 */

class RetrofitTests {

    private val mockMatchHistoryServiceResponses = MockMatchHistoryServiceResponses()

    @Test
    fun `Make sure that we can convert the mock match list id array`() {
        // getMatchIds array string
        val stringResponse = mockMatchHistoryServiceResponses.getMatchList()
        // make intercepter
        val interceptor = MockHttpResponseInterceptor(stringResponse, 200)
        // build retrofit mock
        val retrofitMock = RetrofitFactory().produceMockRetrofitInterface(MatchHistoryService::class.java, interceptor)
        // getMatchIds response
        val call = retrofitMock.fetchMatches(20, 23,2)

        val response : Response<Result<ArrayList<String>>> =  call.execute()

        val result : Result<ArrayList<String>> = response.getMatchIds()
        assert(result.data.size == 9)
    }

    @Test
    fun `Make sure that we can convert the mock match summary to the correct object`() {
        // getMatchIds array string
        val stringResponse = mockMatchHistoryServiceResponses.getMatchSummary(1)
        // make intercepter
        val interceptor = MockHttpResponseInterceptor(stringResponse, 200)
        // build retrofit mock
        val retrofitMock = RetrofitFactory().produceMockRetrofitInterface(MatchHistoryService::class.java, interceptor)
        // getMatchIds response
        val call = retrofitMock.fetchMatchSummary(20, 23)

        val response : Response<Result<MatchHistoryCardData>> =  call.execute()

        val result : Result<MatchHistoryCardData> = response.getMatchHistoryCardData()
        assert(result.data.champId == 1.toLong())
    }
}
