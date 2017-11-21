package com.teamunemployment.lolanalytics.io

import com.teamunemployment.lolanalytics.Data.model.Data
import com.teamunemployment.lolanalytics.Data.model.DoubleWrapper
import com.teamunemployment.lolanalytics.Data.model.LongWrapper
import com.teamunemployment.lolanalytics.Data.model.MatchHistoryData
import com.teamunemployment.lolanalytics.Data.model.MatchIdWrapper
import com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.Model.MatchHistoryCardData
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatCollection
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatDefinitionWrapper

import java.util.ArrayList

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Josiah Kendall
 */

interface RESTApiExecutor {

    /**
     * Fetch Recent head to head stats for a summoner in a specific role
     * @param summonerId The id of the summoner that we want the info for.
     * @return A pojo with a bunch of stats on it.
     */
    @GET("FetchTopStats/{SummonerId}")
    fun GetTopStatsForSummoner(@Path("SummonerId") summonerId: Long): Observable<Data>

    @GET("FetchJungleStats/{SummonerId}")
    fun GetJungleStatsForSummoner(@Path("SummonerId") summonerId: Long): Observable<Data>

    @GET("FetchMidStats/{SummonerId}")
    fun GetMidStatsForSummoner(@Path("SummonerId") summonerId: Long): Observable<Data>

    @GET("FetchAdcStats/{SummonerId}")
    fun GetAdcStatsForSummoner(@Path("SummonerId") summonerId: Long): Observable<Data>

    @GET("FetchSupportStats/{SummonerId}")
    fun GetSupportStats(@Path("SummonerId") summonerId: Long): Observable<Data>

    /**
     * Fetch the summonerId of a summoner given the summoner name and the summoner region.
     * @param summonerName
     * @param region
     * @return The summonerId, or -1 if not found.
     */
    @GET("FetchSummonerId/{SummonerName}/{Region}")
    fun GetSummonerId(@Path("SummonerName") summonerName: String, @Path("Region") region: String): Observable<LongWrapper>

    @GET("FetchWinRate/{SummonerId}/{Role}")
    fun GetWinRateForRole(@Path("SummonerId") summonerId: Long, @Path("Role") role: String): Observable<DoubleWrapper>

    @GET("FetchMatchList/{UserId}/{Role}")
    fun GetMatchListForSummonerInSpecificRole(@Path("UserId") summonerId: Long, @Path("Role") role: Int): Observable<MatchHistoryData>

    @GET("GetStatHistory/{Role}/{SummonerId}/{StatId}")
    fun GetAnalysisStatCollection(@Path("Role") role: Int, @Path("SummonerId") summonerId: Long, statId: Int): Observable<StatCollection>

    @GET("FetchPerformance/{MatchId}/{UserId}")
    fun GetMatchHistoryCardData(@Path("MatchId") matchId: Long, @Path("UserId") userId: Long): Observable<MatchHistoryCardData>

    @GET("FetchStatDefinitions/{SummonerId}/{Role}")
    fun GetStatDefinitions(@Path("SummonerId") summonerId: Long, @Path("Role") role: Int): Observable<StatDefinitionWrapper>
}
