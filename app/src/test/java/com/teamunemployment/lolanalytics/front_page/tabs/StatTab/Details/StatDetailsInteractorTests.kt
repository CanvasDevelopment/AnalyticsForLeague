package com.teamunemployment.lolanalytics.front_page.tabs.StatTab.Details

import com.teamunemployment.lolanalytics.Utils.getStatDetails
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.AnalysisService
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen.model.StatDetailsDataModel
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import com.teamunemployment.lolanalytics.mock.MockAnalysisServiceResponses
import com.teamunemployment.lolanalytics.mock.MockHttpResponseInterceptor
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

/**
 * Created by Josiah Kendall
 */
class StatDetailsInteractorTests {

    @Test
    fun `Test that we can load the json`() {
        val mockAnalysisResponses = MockAnalysisServiceResponses()
        val stringResponse = mockAnalysisResponses.getStatDetails()
        // make intercepter
        val interceptor = MockHttpResponseInterceptor(stringResponse, 200)
        val analysisService = RetrofitFactory().produceMockRetrofitInterface(AnalysisService::class.java, interceptor)

        // getMatchIds response
        val call = analysisService.getStatDetails("")

        val response : Response<Result<StatDetailsDataModel>> =  call.execute()
        val result : Result<StatDetailsDataModel> = response.getStatDetails()
        Assert.assertEquals(result.data.vsDivision[0].heroStatValue, 57.4f)
        Assert.assertEquals(result.data.vsDivision[0].enemyStatValue, 44.5f)
        Assert.assertEquals(result.data.historical[0][0], 45)
    }
}