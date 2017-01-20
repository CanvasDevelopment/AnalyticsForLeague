package com.teamunemployment.lolanalytics.Base;

import android.content.Context;

import com.teamunemployment.lolanalytics.Data.Data;
import com.teamunemployment.lolanalytics.Data.LongWrapper;
import com.teamunemployment.lolanalytics.Contracts.PresenterContract;
import com.teamunemployment.lolanalytics.Jungle.Model.CardData;
import com.teamunemployment.lolanalytics.RESTService.RESTApiExecutor;

import java.util.ArrayList;

import io.realm.Realm;
import rx.Observable;
import rx.Subscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author Josiah Kendall
 *
 * This is the main interactor for two repositories.
 *  - Locally cached Realm Database
 *  - Remote Server.
 */
public class BaseModel {
    private static final String TAG = "BaseModel";
    private RESTApiExecutor RESTApiExecutor;
    private RealmExecutor realmExecutor;
    private Context context;

    public BaseModel(RESTApiExecutor RESTApiExecutor, RealmExecutor realmExecutor, Context context) {
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
     * @return
     */
    public Observable<Data> CreateCachedDataObservable(final long summonerId, final int lane) {

        // Create an observable for fetching our cached data, as we want to get this off of the main thread.
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
     * @param presenter
     */
    public void FetchData(final PresenterContract presenter, Observable<Data> averagesObservable) {

        // Send the request on a new thread, but observe on the main thread.
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
                        presenter.handleError(e);
                    }

                    @Override
                    public void onNext(Data data) {
                        presenter.addDataToAdapter(new ArrayList<CardData>(data.getItems()));
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

    public Long FetchSummonerIdFromLocalStorage(String summonerName) {
        return new Long(-1);
    }

    private void mapResults(Data data) {


    }

    public void FetchCacheData(final PresenterContract presenterContract, Observable<Data> cachedDataObservable) {
        cachedDataObservable.
                subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Data>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Data data) {
                        presenterContract.addDataToAdapter(new ArrayList<CardData>(data.getItems()));
                    }
                });
    }
}

