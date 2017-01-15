package com.teamunemployment.lolanalytics.Base;

import android.util.Log;

import com.teamunemployment.lolanalytics.Data.Data;
import com.teamunemployment.lolanalytics.Data.LongWrapper;
import com.teamunemployment.lolanalytics.Jungle.Contracts.ModelPresenterContract;
import com.teamunemployment.lolanalytics.Jungle.Model.AdapterPojo;
import com.teamunemployment.lolanalytics.RESTService.Api;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Josiah Kendall
 *
 * The model layer for our jungle fragment view.
 */
public class BaseModel {

    private static final String TAG = "BaseModel";
    private Api api;

    public BaseModel(Api api) {
        this.api = api;
    }

    /**
     * Get the adapter pojos that we require to display on the recycler view.
     * @param summonerId
     */
    public void getCardPojos(long summonerId, ModelPresenterContract modelPresenterContract,
                             final int lane) {
        // TODO sort out this part.
        final String region = "";
        //
        filterRoleToFetch(summonerId, lane, modelPresenterContract);

    }

    /**
     * Decide what data we need to fetch, then fetch it.
     * @param summonerId
     * @param lane
     * @param modelPresenterContract
     */
    private void filterRoleToFetch(long summonerId, int lane, ModelPresenterContract modelPresenterContract) {
        Log.d(TAG, "Found summonerId of : " + summonerId);
        Observable<Data> averagesObservable = null;
        switch (lane) {
            case Statics.TOP:
                averagesObservable = api.GetTopStatsForSummoner(summonerId);
                break;
            case Statics.JUNGLE:
                averagesObservable = api.GetJungleStatsForSummoner(summonerId);
                break;
            case Statics.MID:
                averagesObservable = api.GetMidStatsForSummoner(summonerId);
                break;
            case Statics.ADC:
                averagesObservable = api.GetAdcStatsForSummoner(summonerId);
                break;
            case Statics.SUPPORT:
                averagesObservable = api.GetSupportStats(summonerId);
                break;
        }

        // Fetch the data from the observable that we have created
        fetchData(summonerId, modelPresenterContract, averagesObservable);
    }

    /**
     * Fetch data for top lane
     * @param summonerId
     * @param modelPresenterContract
     */
    private void fetchData(Long summonerId, final ModelPresenterContract modelPresenterContract, Observable<Data> averagesObservable) {

        Log.d(TAG, "Fetching data for summoner with id: " + summonerId);

        // Send the request on a new thread, but observe on the main thread.
        averagesObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Data>() {

                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "Completed loading");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Failed to load card data. error found : " + e.getMessage());
                    }

                    @Override
                    public void onNext(Data adapterPojosDataSource) {
                        modelPresenterContract.addDataToAdapter(new ArrayList<AdapterPojo>(adapterPojosDataSource.items));
                    }
                });
    }

    /**
     * Fetch the summoner id for the summoner with a given name.
     * @param summonerName The user entered summoner name.
     * @param region The region the user is playing at. E.g OCE, NA, EUW etc..
     * @return The summoner id. Is -1 if summoner not found.
     */
    public Observable<LongWrapper> GetSummonerIdUsingSummonerName(String summonerName, String region) {

        Log.d(TAG, "Attempting to fetch Summoner id for summoner " + summonerName+ "in region " + region);

        Observable<LongWrapper> idResultObservable = api.GetSummonerId(summonerName, region);
        return idResultObservable;
    }

    public Long FetchSummonerIdFromLocalStorage(String summonerName) {
        return new Long(-1);
    }



}

