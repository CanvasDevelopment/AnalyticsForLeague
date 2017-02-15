package com.teamunemployment.lolanalytics.Data;

import com.teamunemployment.lolanalytics.Data.model.Data;
import com.teamunemployment.lolanalytics.Data.model.DoubleWrapper;
import com.teamunemployment.lolanalytics.Data.model.LongWrapper;
import com.teamunemployment.lolanalytics.Data.model.MatchHistoryData;
import com.teamunemployment.lolanalytics.Data.model.MatchIdWrapper;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.Model.MatchHistoryCardData;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatCollection;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Josiah Kendall
 */

public interface RESTApiExecutor {

    /**
     * Fetch Recent head to head stats for a summoner in a specific role
     * @param summonerId The id of the summoner that we want the info for.
     * @return A pojo with a bunch of stats on it.
     */
    @GET("FetchTopStats/{SummonerId}")
    Observable<Data> GetTopStatsForSummoner(@Path("SummonerId") long summonerId);

    @GET("FetchJungleStats/{SummonerId}")
    Observable<Data> GetJungleStatsForSummoner(@Path("SummonerId") long summonerId);

    @GET("FetchMidStats/{SummonerId}")
    Observable<Data> GetMidStatsForSummoner(@Path("SummonerId") long summonerId);

    @GET("FetchAdcStats/{SummonerId}")
    Observable<Data> GetAdcStatsForSummoner(@Path("SummonerId") long summonerId);

    @GET("FetchSupportStats/{SummonerId}")
    Observable<Data> GetSupportStats(@Path("SummonerId") long summonerId);

    /**
     * Fetch the summonerId of a summoner given the summoner name and the summoner region.
     * @param summonerName
     * @param region
     * @return The summonerId, or -1 if not found.
     */
    @GET("FetchSummonerId/{SummonerName}/{Region}")
    Observable<LongWrapper> GetSummonerId(@Path("SummonerName") String summonerName, @Path("Region") String region);

    @GET("FetchWinRate/{SummonerId}/{Role}")
    Observable<DoubleWrapper> GetWinRateForRole(@Path("SummonerId") long summonerId, @Path("Role") String role);

    @GET("FetchMatchList/{UserId}/{Role}")
    Observable<MatchHistoryData> GetMatchListForSummonerInSpecificRole(@Path("UserId") long summonerId, @Path("Role") int role);

    @GET("GetAnalysisStatCollection/{Role}/{SummonerId}")
    Observable<StatCollection> GetAnalysisStatCollection(@Path("Role") int role, @Path("SummonerId") long summonerId);

    @GET("FetchPerformance/{MatchId}/{UserId}")
    Observable<MatchHistoryCardData> GetMatchHistoryCardData(@Path("MatchId") long matchId, @Path("UserId") long userId);
}
