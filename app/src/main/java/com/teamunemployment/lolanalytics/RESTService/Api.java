package com.teamunemployment.lolanalytics.RESTService;

import com.teamunemployment.lolanalytics.Data.Data;
import com.teamunemployment.lolanalytics.Data.LongWrapper;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author Josiah Kendall
 */

public interface Api {

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

    @GET("FetchADCStats/{SummonerId}")
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

}
