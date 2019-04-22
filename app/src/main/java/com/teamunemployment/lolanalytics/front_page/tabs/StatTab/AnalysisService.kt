package com.teamunemployment.lolanalytics.front_page.tabs.StatTab

import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen.model.StatDetailsDataModel
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.Model.StatSummary
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Josiah Kendall
 */
interface AnalysisService {

    @GET("/getStuff")
    fun getStatList() : Call<Result<ArrayList<StatSummary>>>

    @GET("/fullStatCard/{url}")
    fun getFullCardStat(@Path("url")url : String) : Call<Result<ArrayList<HeadToHeadStat>>>

    @GET("/details/{url}")
    fun getStatDetails(@Path("url") url : String) : Call<Result<StatDetailsDataModel>>

}