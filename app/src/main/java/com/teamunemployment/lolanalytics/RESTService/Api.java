package com.teamunemployment.lolanalytics.RESTService;

import com.teamunemployment.lolanalytics.RecentHeadToHeadAverage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

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
    Call<RecentHeadToHeadAverage> GetHeadToHeadAverageForSummonerAndRole(@Path("Role") String role, @Path("SummonerName") String summonerName);
}
