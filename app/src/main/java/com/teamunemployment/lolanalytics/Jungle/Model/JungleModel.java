package com.teamunemployment.lolanalytics.Jungle.Model;

import android.util.Log;

import com.teamunemployment.lolanalytics.Jungle.Contracts.ModelPresenterContract;
import com.teamunemployment.lolanalytics.RESTService.Api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Josiah Kendall
 *
 * The model layer for our jungle fragment view.
 */
public class JungleModel {

    private static final String TAG = "JungleModel";

    private Api api;

    public JungleModel(Api api) {
        this.api = api;
    }

    /**
     * Get the adapter pojos that we require to display on the recycler view.
     * @param summonerName
     */
    public void getCardPojos(String summonerName, final ModelPresenterContract modelPresenterContract) {

        Log.d(TAG, "Starting card details fetch");
        // Create single with retrofit.
        Observable<List<JungleAdapterPojo>> averagesObservable = api.GetHeadToHeadAverageForSummonerAndRole("JUNGLE", summonerName);

        // Send the request on a new thread, but observe on the main thread.
        averagesObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<JungleAdapterPojo>>() {

                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "Completed loading");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Failed to load card data. error found : " + e.getMessage());
                    }

                    @Override
                    public void onNext(List<JungleAdapterPojo> jungleAdapterPojos) {
                        modelPresenterContract.addDataToAdapter(new ArrayList<JungleAdapterPojo>(jungleAdapterPojos));
//                        Iterator<JungleAdapterPojo> pojoIterator = jungleAdapterPojos.iterator();
////                        while (pojoIterator.hasNext()) {
////                            JungleAdapterPojo jungleAdapterPojo = pojoIterator.next();
////                            Log.d(TAG, "On Next. enemy stats: " + jungleAdapterPojo.enemyStats);
////                            modelPresenterContract.addStatToList(jungleAdapterPojo);
////                        }
                    }
                });
    }
}

