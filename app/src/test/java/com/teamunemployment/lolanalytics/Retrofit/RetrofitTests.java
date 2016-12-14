package com.teamunemployment.lolanalytics.Retrofit;

import android.content.Context;

import com.teamunemployment.lolanalytics.RESTService.Api;
import com.teamunemployment.lolanalytics.RecentHeadToHeadAverage;
import com.teamunemployment.lolanalytics.mock.MockHttpClient;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jek40 on 14/12/2016.
 */

public class RetrofitTests {

    @Test
    public void TestThatWeCanBindAveragesDataThatGetsReturnedByTheServerWithRetrofit() throws IOException {

        Context context = Mockito.mock(Context.class);
        // Our mock return.
        String result = "{" +
                "\"averageCsEarlyGame\" : 67.23, " +
                "\"averageCsEarlyGameEnemy\" : 57.23," +
                "\"averageCsMidGame\" : 86.41," +
                "\"averageCsMidGameEnemy\" : 78.34," +
                "\"averageTotalCs\": 212.34," +
                "\"averageTotalCs\": 187.5," +
                "\"damageDealtEarlyGame\" : 1234," +
                "\"damageDealtEarlyGameEnemy\" : 1234," +
                "\"damageDealtMidGame\" : 1234," +
                "\"damageDealtMidGameEnemy\" : 1234," +
                "\"totalDamageDealt\" : 2000," +
                "\"totalDamageDealtEnemy\" : 2000," +
                "\"damageTakenEarlyGame\" : 1244," +
                "\"damageTakenEarlyGameEnemy\" : 1244," +
                "\"damageTakenMidGame\" : 1233," +
                "\"damagetakenTotal\" : 2344," +
                "\"KDA\" : 3.12," +
                "\"averageKills\" : 5.34," +
                "\"averageKillsEnemy\" : 4.35," +
                "\"averageDeaths\" : 4.56," +
                "\"averageDeathsEnemy\" : 4.76," +
                "\"averageAssists\" : 6.34," +
                "\"averageAssistsEnemy\" : 8.34 }";
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new MockHttpClient(context, result, 200)
                ).build();

        Retrofit retrofit = new Retrofit.Builder()
                // .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http:tete.com/irrelevant/foo/")
                .client(okHttpClient)
                .build();

        Api api = retrofit.create(Api.class);
        Call<RecentHeadToHeadAverage> averagesCall = api.GetHeadToHeadAverageForSummonerAndRole("foo", "bar");
        RecentHeadToHeadAverage averages = averagesCall.execute().body();
        Assert.assertEquals(6.34, averages.averageAssists);
    }
}
