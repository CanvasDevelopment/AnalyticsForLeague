package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab

import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatSummary
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

}