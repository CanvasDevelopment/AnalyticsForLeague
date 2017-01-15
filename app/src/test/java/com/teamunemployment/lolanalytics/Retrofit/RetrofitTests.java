package com.teamunemployment.lolanalytics.Retrofit;

import android.content.Context;

import com.teamunemployment.lolanalytics.Jungle.Model.AdapterPojo;
import com.teamunemployment.lolanalytics.RESTService.Api;
import com.teamunemployment.lolanalytics.Mock.MockHttpClient;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;

/**
 * Created by jek40 on 14/12/2016.
 */

public class RetrofitTests {

    @Test
    public void TestThatWeCanBindAveragesDataThatGetsReturnedByTheServerWithRetrofit() throws IOException {

        Context context = mock(Context.class);
        // Our mock return.
        String result = "[{" +
                "\"title\" : \"Creep Score First 10 Minutes\"," +
                "\"enemyStats\" : 5.21," +
                "\"friendlyStats\" : 6.45" +
                " }," +
                "{\"title\" : \"Creep Score 10 -20 minutes\"," +
                "\"enemyStats\" : 5.65," +
                "\"friendlyStats\" : 5.46 " +
                "}]";

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new MockHttpClient(result, 200)
                ).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http:tete.com/irrelevant/foo/")
                .client(okHttpClient)
                .build();

        Api api = retrofit.create(Api.class);
        Observable<AdapterPojo> averagesObservable = api.GetHeadToHeadAverageForSummonerAndRole("JUNGLE", "bar");
        averagesObservable.subscribeOn(Schedulers.newThread())
                .observeOn(mock(Scheduler.class))
                .subscribe(new Subscriber<AdapterPojo>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AdapterPojo adapterPojo) {
                        Assert.assertEquals(5.21, adapterPojo.enemyStats);
                    }
                });
    }
}
