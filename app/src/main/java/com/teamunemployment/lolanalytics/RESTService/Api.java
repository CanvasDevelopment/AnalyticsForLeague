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
     * @param role the specified role (e.g JUNGLE, TOP, BOT, SUPPORT MID)
     * @param summonerName
     * @return A pojo with a bunch of stats on it.
     */
    @GET("BASE_URL/HeadToHead/{Role}/{SummonerName}")
    Observable<List<JungleAdapterPojo>> GetHeadToHeadAverageForSummonerAndRole(@Path("Role") String role, @Path("SummonerName") String summonerName);


}
