package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab

import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatCard
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Josiah Kendall
 */
interface PlayerAnalysisRemoteRepo {


    @GET("analysis/v1/getStatList/{summonerId}/{lane}/{champ}/{games}")
    fun fetchStatList(@Path("summonerId")summonerId: Long,
                      @Path("lane") lane : Int,
                      @Path("champ") champ: Int,
                      @Path("games")games : Int) : Call<Result<StatList>>

    @GET("analysis/v1/getStatList/{summonerId}/{lane}/{games}")
    fun fetchStatList(@Path("summonerId")summonerId: Long,
                      @Path("lane") lane : Int,
                      @Path("games")games : Int) : Call<Result<StatList>>

    @GET("analysis/v1/{statUrl}")
    fun fetchIndividualStat(url: String) : Call<Result<ArrayList<HeadToHeadStat>>>
}