package com.teamunemployment.lolanalytics.Jungle;

import android.content.Context;

import com.teamunemployment.lolanalytics.Jungle.Contracts.ModelPresenterContract;
import com.teamunemployment.lolanalytics.Jungle.Model.JungleAdapterPojo;
import com.teamunemployment.lolanalytics.Jungle.Model.JungleModel;
import com.teamunemployment.lolanalytics.Jungle.PresentationLayer.JunglePresenter;
import com.teamunemployment.lolanalytics.RESTService.Api;
import com.teamunemployment.lolanalytics.mock.MockHttpClient;

import junit.framework.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.mockito.Mockito.mock;

/**
 * @author Josiah Kendall.
 */

public class JungleModelTests {

    @Test
    public void TestThatWeCanCreateAdapterPojosFromOurStatsPojo() throws IOException {

        // Not sure if this test is needed. We have the same one
//        Context context = Mockito.mock(Context.class);
//        // Our mock return.
//        String result = "{" +
//                "\"averageCsEarlyGame\" : 67.23, " +
//                "\"averageCsEarlyGameEnemy\" : 57.23," +
//                "\"averageCsMidGame\" : 86.41," +
//                "\"averageCsMidGameEnemy\" : 78.34," +
//                "\"averageTotalCs\": 212.34," +
//                "\"averageTotalCs\": 187.5," +
//                "\"damageDealtEarlyGame\" : 1234," +
//                "\"damageDealtEarlyGameEnemy\" : 1234," +
//                "\"damageDealtMidGame\" : 1234," +
//                "\"damageDealtMidGameEnemy\" : 1234," +
//                "\"totalDamageDealt\" : 2000," +
//                "\"totalDamageDealtEnemy\" : 2000," +
//                "\"damageTakenEarlyGame\" : 1244," +
//                "\"damageTakenEarlyGameEnemy\" : 1244," +
//                "\"damageTakenMidGame\" : 1233," +
//                "\"damagetakenTotal\" : 2344," +
//                "\"KDA\" : 3.12," +
//                "\"averageKills\" : 5.34," +
//                "\"averageKillsEnemy\" : 4.35," +
//                "\"averageDeaths\" : 4.56," +
//                "\"averageDeathsEnemy\" : 4.76," +
//                "\"averageAssists\" : 6.34," +
//                "\"averageAssistsEnemy\" : 8.34 }";
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(
//                        new MockHttpClient(context, result, 200)
//                ).build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                // .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("http:tete.com/irrelevant/foo/")
//                .client(okHttpClient)
//                .build();
//
//        Api api = retrofit.create(Api.class);
//
//        JungleModel jungleModel = new JungleModel(api);
//        ModelPresenterContract modelPresenterContract = mock(ModelPresenterContract.class);
//        jungleModel.getCardPojos("test", modelPresenterContract);
//        Assert.assertEquals(pojos.get(0).enemyStats, 57.23);
    }

}

