package com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab

import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.DetailsScreen.MatchPerformanceDetails
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.MatchHistoryCardData
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.MatchIdentifier
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

/**
 * @author Josiah Kendall
 */
interface MatchHistoryService {

    @GET("match/v1/loadTwentyMatchIds/{role}/{startingPoint}/{summonerId}")
    fun fetchMatches(@Path("role") role : Int,
                     @Path("startingPoint") startingPoint : Int,
                     @Path("summonerId") summonerId : String) : Call<Result<ArrayList<MatchIdentifier>>>

    @GET("match/v1/loadTwentyMatchIds/{role}/{startingPoint}/{summonerId}/{champId}")
    fun fetchMatchesWithChamp(@Path("role") role : Int,
                            @Path("champId") champId : Int,
                            @Path("startingPoint") startingPoint : Int,
                            @Path("summonerId") summonerId : String) : Call<Result<ArrayList<MatchIdentifier>>>

    @GET("match/v1/loadMatchSummary/{role}/{matchId}/{summonerId}")
    fun fetchMatchSummary(
                        @Path("role") role : Int,
                        @Path("matchId") matchId : Long,
                        @Path("summonerId") summonerId : String) : Call<Result<MatchHistoryCardData>>

    @GET
    fun fetchMatchDetails(@Url detailsUrl : String) : Call<Result<MatchPerformanceDetails>>
}