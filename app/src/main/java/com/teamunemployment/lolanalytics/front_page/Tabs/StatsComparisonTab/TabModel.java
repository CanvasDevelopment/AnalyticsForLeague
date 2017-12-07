package com.teamunemployment.lolanalytics.front_page.Tabs.StatsComparisonTab;

import android.content.Context;

import com.teamunemployment.lolanalytics.data.Statics;
import com.teamunemployment.lolanalytics.data.model.Data;
import com.teamunemployment.lolanalytics.data.model.LongWrapper;
import com.teamunemployment.lolanalytics.front_page.Tabs.StatsComparisonTab.Model.CardData;
import com.teamunemployment.lolanalytics.io.RESTApiExecutor;
import com.teamunemployment.lolanalytics.io.RealmExecutor;
import com.teamunemployment.lolanalytics.Utils.Network;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;


/**
 * @author Josiah Kendall
 *
 * This is the base interactor for two repositories.
 *  - Locally cached Realm Database
 *  - Remote Server.
 */
public class TabModel {
    // Logging tag'
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
     * Decide what value we need to fetch, then fetch it.
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
     * Create an observable that we can use to fetch the cached value from our database.
     * @param summonerId
     * @param lane
     * @return Our ready observble.
     */
    public Observable<Data> CreateCachedDataObservable(final long summonerId, final int lane) {
        // Create an observable for fetching our cached value, as we want to get this off of the base thread.
        Observable observable = Observable.create((ObservableEmitter<Data> emitter) -> {
            Realm.init(context);
            Realm realm = Realm.getDefaultInstance();
            Data data = realmExecutor.FindDataForRole(lane, summonerId, realm);
            emitter.onNext(data);
            emitter.onComplete();
        });

        return observable;
    }

    /**
     * Fetch value using an observable, and the call the write value object on the presenter that was used.
     * This is done to keep the presenter as thin as possible.
     * @param tabPresenterContract
     */
    public void FetchData(final TabModelContract.Presenter tabPresenterContract, Observable<Data> averagesObservable) {
        Network network = new Network();
        // TODO find better solution to this here. Also notify the user.
        if (!network.isConnectingToInternet(context)) {
            return;
        }

        Realm realm = Realm.getDefaultInstance();

        // Send the request on a new thread, but observe on the base thread.
        averagesObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
                // Use the urlMap to write to the database.
                .map(data -> {
                    realmExecutor.WriteDataObjectToRealm(data, realm);
                    return data;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    tabPresenterContract.addDataToAdapter(new ArrayList<CardData>(data.getItems()));
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

    public void FetchCacheData(final TabModelContract.Presenter tabPresenterContract, Observable<Data> cachedDataObservable) {
        cachedDataObservable.
                // Currently running this on the main thread, because issues arise with creating realm objects on one thread then using them on another.
                subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    tabPresenterContract.addDataToAdapter(new ArrayList<CardData>(data.getItems()));
                });
    }
}

