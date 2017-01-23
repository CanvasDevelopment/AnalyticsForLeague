package com.teamunemployment.lolanalytics.StatsComparisonTab;

import android.content.Context;

import com.teamunemployment.lolanalytics.Data.Statics;
import com.teamunemployment.lolanalytics.Data.model.Data;
import com.teamunemployment.lolanalytics.Data.model.LongWrapper;
import com.teamunemployment.lolanalytics.StatsComparisonTab.Model.CardData;
import com.teamunemployment.lolanalytics.Data.RESTApiExecutor;
import com.teamunemployment.lolanalytics.Data.RealmExecutor;

import java.util.ArrayList;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Observable;
import rx.Subscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author Josiah Kendall
 *
 * This is the base interactor for two repositories.
 *  - Locally cached Realm Database
 *  - Remote Server.
 */
public class TabModel {
    private static final String TAG = "TabModel";
    private RESTApiExecutor RESTApiExecutor;
    private RealmExecutor realmExecutor;
    private Context context;

    @Inject
    public TabModel(RESTApiExecutor RESTApiExecutor, RealmExecutor realmExecutor, Context context) {
        this.RESTApiExecutor = RESTApiExecutor;
        this.realmExecutor = realmExecutor;
        this.context = context;
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
                averagesObservable = RESTApiExecutor.GetTopStatsForSummoner(summonerId);
                break;
            case Statics.JUNGLE:
                averagesObservable = RESTApiExecutor.GetJungleStatsForSummoner(summonerId);
                break;
            case Statics.MID:
                averagesObservable = RESTApiExecutor.GetMidStatsForSummoner(summonerId);
                break;
            case Statics.ADC:
                averagesObservable = RESTApiExecutor.GetAdcStatsForSummoner(summonerId);
                break;
            case Statics.SUPPORT:
                averagesObservable = RESTApiExecutor.GetSupportStats(summonerId);
                break;
        }

        return averagesObservable;
    }

    /**
     * Create an observable that we can use to fetch the cached data from our database.
     * @param summonerId
     * @param lane
     * @return Our ready observble.
     */
    public Observable<Data> CreateCachedDataObservable(final long summonerId, final int lane) {

        // Create an observable for fetching our cached data, as we want to get this off of the base thread.
        Observable observable = Observable.create(new Observable.OnSubscribe<Data>() {
            @Override
            public void call(Subscriber<? super Data> subscriber) {
                Realm.init(context);
                Realm realm = Realm.getDefaultInstance();
                Data data = realmExecutor.FindDataForRole(lane, summonerId, realm);
                subscriber.onNext(data);
                subscriber.onCompleted();
            }
        });

        return observable;
    }

    /**
     * Fetch data using an observable, and the call the write data object on the presenter that was used.
     * This is done to keep the presenter as thin as possible.
     * @param tabPresenterContract
     */
    public void FetchData(final TabContract.Presenter tabPresenterContract, Observable<Data> averagesObservable) {

        // Send the request on a new thread, but observe on the base thread.
        averagesObservable
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
                // Use the map to write to the database.
                .map(new Func1<Data, Data>() {
                    @Override
                    public Data call(Data data) {
                        realmExecutor.WriteDataObjectToRealm(data);
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
                        tabPresenterContract.handleError(e);
                    }

                    @Override
                    public void onNext(Data data) {
                        tabPresenterContract.addDataToAdapter(new ArrayList<CardData>(data.getItems()));
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
        Observable<LongWrapper> idResultObservable = RESTApiExecutor.GetSummonerId(summonerName, region);
        return idResultObservable;
    }

    // Not used yet, but will be.
    public Long FetchSummonerIdFromLocalStorage(String summonerName) {
        return new Long(-1);
    }

    public void FetchCacheData(final TabContract.Presenter tabPresenterContract, Observable<Data> cachedDataObservable) {
        cachedDataObservable.
                subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Data>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        tabPresenterContract.handleError(e);
                    }

                    @Override
                    public void onNext(Data data) {
                        tabPresenterContract.addDataToAdapter(new ArrayList<CardData>(data.getItems()));
                    }
                });
    }
}

