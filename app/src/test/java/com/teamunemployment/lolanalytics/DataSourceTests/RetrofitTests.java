package com.teamunemployment.lolanalytics.DataSourceTests;

import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;

/**
 * Created by jek40 on 14/12/2016.
 */

public class RetrofitTests {

    // TODO
    @Test
    public void TestThatWeCanBindAveragesDataThatGetsReturnedByTheServerWithRetrofit() throws IOException {

//        // Our mock return.
//        String result = "[{" +
//                "\"title\" : \"Creep Score First 10 Minutes\"," +
//                "\"enemyStats\" : 5.21," +
//                "\"friendlyStats\" : 6.45" +
//                " }," +
//                "{\"title\" : \"Creep Score 10 -20 minutes\"," +
//                "\"enemyStats\" : 5.65," +
//                "\"friendlyStats\" : 5.46 " +
//                "}]";
//
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(
//                        new MockHttpClient(result, 200)
//                ).build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .baseUrl("http:tete.com/irrelevant/foo/")
//                .client(okHttpClient)
//                .build();
//
//        RESTApiExecutor rESTApiExecutor = retrofit.create(RESTApiExecutor.class);
//        Observable<Data> averagesObservable = rESTApiExecutor.GetMidStatsForSummoner(1234567);
//        averagesObservable.subscribeOn(Schedulers.newThread())
//                .observeOn(mock(Scheduler.class))
//                .subscribe(new Observer<Data>() {
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Data adapterPojo) {
//                        Assert.assertEquals(5.21, adapterPojo.items.get(0).getEnemyStats());
//                    }
//                });
    }
}
