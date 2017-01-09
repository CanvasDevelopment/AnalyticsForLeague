package com.teamunemployment.lolanalytics.RESTService;

import com.teamunemployment.lolanalytics.Jungle.Model.JungleAdapterPojo;
import com.teamunemployment.lolanalytics.RecentHeadToHeadAverage;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.Single;

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
    Observable<List<JungleAdapterPojo>> GetTopStatsForSummoner(@Path("SummonerName") long summonerId);

    @GET("FetchSummonerId/{SummonerName}")
    Observable<Long> GetSummonerName(@Path("SummonerName") String summonerName);

}
