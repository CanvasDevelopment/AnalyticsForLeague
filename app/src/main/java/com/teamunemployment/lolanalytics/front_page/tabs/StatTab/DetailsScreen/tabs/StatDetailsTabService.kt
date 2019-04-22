package com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen.tabs

import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.MatchIdentifier
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen.model.StatDetailsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StatDetailsTabService {

    @GET("analysis/v1/statDetails/{summonerId}/{games}/{lane}/{statName}")
    fun fetchMatches(@Path("summonerId") summonerId : String,
                     @Path("games") games : Int,
                     @Path("lane") lane : String,
                     @Path("statName") statName : String) : Call<Result<StatDetailsData>>

    @GET("analysis/v1/statDetails/{summonerId}/{games}/{lane}/{statName}/{champKey}")
    fun fetchMatches(@Path("summonerId") summonerId : String,
                     @Path("games") games : Int,
                     @Path("lane") lane : String,
                     @Path("statName") statName : String,
                     @Path("champKey") champKey : String) : Call<Result<StatDetailsData>>
}