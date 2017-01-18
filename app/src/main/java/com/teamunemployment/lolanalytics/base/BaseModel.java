package com.teamunemployment.lolanalytics.Base;

import com.teamunemployment.lolanalytics.Data.Data;
import com.teamunemployment.lolanalytics.Data.LongWrapper;
import com.teamunemployment.lolanalytics.Contracts.ModelPresenterContract;
import com.teamunemployment.lolanalytics.Jungle.Model.AdapterPojo;
import com.teamunemployment.lolanalytics.RESTService.Api;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author Josiah Kendall
 *
 * The model layer for our jungle fragment view.
 */
public class BaseModel {

    private static final String TAG = "BaseModel";
    private Api api;
    private RealmInterface realmInterface;
    private String mLane;
    public BaseModel(Api api, RealmInterface realmInterface) {
        this.api = api;
        this.realmInterface = realmInterface;
    }

    /**
     * Decide what data we need to fetch, then fetch it.
     * @param summonerId The summonerId to call
     * @param lane
     */
    public Observable<Data> CreateLaneDataObservable(long summonerId, int lane) {
        Observable<Data> averagesObservable = null;
        switch (lane) {
            case Statics.TOP:
                averagesObservable = api.GetTopStatsForSummoner(summonerId);
                mLane = "TOP";
                break;
            case Statics.JUNGLE:
                mLane = "JUNGLE";
                averagesObservable = api.GetJungleStatsForSummoner(summonerId);
                break;
            case Statics.MID:
                mLane = "MID";
                averagesObservable = api.GetMidStatsForSummoner(summonerId);
                break;
            case Statics.ADC:
                mLane = "ADC";
                averagesObservable = api.GetAdcStatsForSummoner(summonerId);
                break;
            case Statics.SUPPORT:
                mLane = "SUPPORT";
                averagesObservable = api.GetSupportStats(summonerId);
                break;
        }

        return averagesObservable;
    }

    public Observable<Data> CreateCachedDataObservable(final long summonerId, final int lane) {

        // Create an observable for fetching our cached data, as we want to get this off of the main thread.
        Observable observable = Observable.create(new Observable.OnSubscribe<Data>() {
            @Override
            public void call(Subscriber<? super Data> subscriber) {
                Data data = realmInterface.FindDataForRole(lane, summonerId);
                subscriber.onNext(data);
                subscriber.onCompleted();
            }
        });

        return observable;
    }

    public Data FetchCachedData(long summonerId, int lane) {
        Data data = realmInterface.FindDataForRole(lane, summonerId );
        return data;
    }

    /**
     * Fetch data for top lane
     * @param modelPresenterContract
     */
    public void FetchData(final ModelPresenterContract modelPresenterContract, Observable<Data> averagesObservable) {

        // Send the request on a new thread, but observe on the main thread.
        averagesObservable
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
                .map(new Func1<Data, Data>() {
                    @Override
                    public Data call(Data data) {
                        realmInterface.WriteDataObjectToRealm(data);
                        return data;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Data>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        modelPresenterContract.handleError(e);
                    }

                    @Override
                    public void onNext(Data data) {
                        modelPresenterContract.addDataToAdapter(new ArrayList<AdapterPojo>(data.getItems()));
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

        Observable<LongWrapper> idResultObservable = api.GetSummonerId(summonerName, region);
        return idResultObservable;
    }

    public Long FetchSummonerIdFromLocalStorage(String summonerName) {
        return new Long(-1);
    }

    private void mapResults(Data data) {


    }

}

