package com.teamunemployment.lolanalytics.front_page.tabs.StatTab

import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.Model.StatSummary
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Josiah Kendall
 */
interface PlayerAnalysisRemoteRepo {


    @GET("analysis/v1/getStatList/{summonerId}/{lane}/{champ}/{games}")
    fun fetchStatList(@Path("summonerId")summonerId : String,
                      @Path("lane") lane : String,
                      @Path("champ") champ: Int,
                      @Path("games")games : Int) : Call<Result<ArrayList<StatSummary>>>

    @GET("analysis/v1/getStatList/{summonerId}/{lane}/{games}")
    fun fetchStatList(@Path("summonerId")summonerId : String,
                      @Path("lane") lane : String,
                      @Path("games")games : Int) : Call<Result<ArrayList<StatSummary>>>

    @GET("analysis/v1/{url}")
    fun fetchIndividualStat(@Path("url", encoded = true) url: String) : Call<Result<ArrayList<HeadToHeadStat>>>
}