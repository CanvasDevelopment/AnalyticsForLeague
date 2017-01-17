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
     * Decide what data we need to fetch, then fetch it.
     * @param summonerId The summonerId to call
     * @param lane
     */
    public Observable<Data> CreateLaneDataObservable(long summonerId, int lane) {
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

        return averagesObservable;
    }

    /**
     * Fetch data for top lane
     * @param modelPresenterContract
     */
    public void FetchData(final ModelPresenterContract modelPresenterContract, Observable<Data> averagesObservable) {

        // Send the request on a new thread, but observe on the main thread.
        averagesObservable
            .subscribeOn(Schedulers.newThread())
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

        Observable<LongWrapper> idResultObservable = api.GetSummonerId(summonerName, region);
        return idResultObservable;
    }

    public Long FetchSummonerIdFromLocalStorage(String summonerName) {
        return new Long(-1);
    }



}

