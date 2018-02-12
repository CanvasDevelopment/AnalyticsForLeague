package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab

import com.teamunemployment.lolanalytics.data.model.DoubleWrapper
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Model.ArrayListWrapper
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Model.MatchHistoryCardData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Josiah Kendall
 */
interface MatchHistoryService {

    @GET("/getStuff/{numberOfMatches}/{summonerId}/{role}")
    fun fetchMatches(@Path("numberOfMatches") numberOfMatches : Int, @Path("summonerId") summonerId : Long, @Path("role") role : Int) : Call<Result<ArrayList<String>>>

    @GET("/matches/card/summary/{matchId}/{summonerId}")
    fun fetchMatchSummary(@Path("matchId") matchId : Long, @Path("summonerId") summonerId: Long) : Call<Result<MatchHistoryCardData>>

    fun fetchMatches(numberOfMatches : Int, summonerId : Long, role : Int, champId : Int)
}