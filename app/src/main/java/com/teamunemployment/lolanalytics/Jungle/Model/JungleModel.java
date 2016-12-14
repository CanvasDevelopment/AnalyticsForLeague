package com.teamunemployment.lolanalytics.Jungle.Model;

import com.teamunemployment.lolanalytics.Jungle.PresentationLayer.JunglePresenter;
import com.teamunemployment.lolanalytics.RESTService.Api;
import com.teamunemployment.lolanalytics.RecentHeadToHeadAverage;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author Josiah Kendall
 *
 * The model layer for our jungle fragment view.
 */
public class JungleModel {

    private Api api;

    public JungleModel(Api api) {
        this.api = api;
    }

    /**
     * Get the adapter pojos that we require to display on the recycler view.
     * @param summonerName
     * @return
     */
    public ArrayList<JungleAdapterPojo> getCardPojos(String summonerName) {

        ArrayList<JungleAdapterPojo> pojos = new ArrayList<>();

        try {
            Call<RecentHeadToHeadAverage> averagesCall = api.GetHeadToHeadAverageForSummonerAndRole("JUNGLE", summonerName);
            Response<RecentHeadToHeadAverage> averagesResponse = averagesCall.execute();

            // If we are not 200 then we have an issue.
            if (averagesResponse.code() != 200) {
                //presenter.showMessageToUser("Error connecting to server. Please check internet access and try again");
                return pojos;
            }

            // create pojos.
            RecentHeadToHeadAverage averagesStats = averagesResponse.body();
            if (averagesStats == null) {
                //presenter.showMessageToUser("Error connecting to server. Please check internet access and try again");
            }

            JungleAdapterPojo averageKills = new JungleAdapterPojo(averagesStats.averageKillsEnemy, averagesStats.averageKills);
            JungleAdapterPojo averageDeaths = new JungleAdapterPojo(averagesStats.averageDeathsEnemy, averagesStats.averageDeaths);
            JungleAdapterPojo averageCsEarly = new JungleAdapterPojo(averagesStats.averageCsEarlyGameEnemy, averagesStats.averageCsEarlyGame);
            JungleAdapterPojo averageCSMid = new JungleAdapterPojo(averagesStats.averageCsMidGameEnemy, averagesStats.averageCsMidGame);
            JungleAdapterPojo averageCsTotal = new JungleAdapterPojo(averagesStats.averageTotalCsEnemy, averagesStats.averageTotalCs);

            pojos.add(averageCsEarly);
            pojos.add(averageCSMid);
            pojos.add(averageCsTotal);
            pojos.add(averageDeaths);
            pojos.add(averageKills);

        } catch (IOException e) {
            // This is a pretty serious error. Basically means that we cant access server.
            e.printStackTrace();
           // presenter.showMessageToUser("Error connecting to server. Please check internet access and try again");
        }
        // For test purposes atm.
       return pojos;
    }

}
