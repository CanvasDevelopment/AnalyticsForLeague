package com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab;

import com.teamunemployment.lolanalytics.Data.RESTApiExecutor;
import com.teamunemployment.lolanalytics.Data.RealmExecutor;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatCollection;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatSummary;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * @author Josiah Kendall
 *
 * The persisitance interactor to load data from the local and remote storage for the analysis tab.
 */

public class PlayerAnalysisPersistanceInteracter {

    private RESTApiExecutor restApiExecutor;
    private RealmExecutor realmExecutor;

    @Inject
    public PlayerAnalysisPersistanceInteracter(RESTApiExecutor restApiExecutor, RealmExecutor realmExecutor) {
        this.restApiExecutor = restApiExecutor;
        this.realmExecutor = realmExecutor;
    }

    private void loadStatAnalysisCardObjects(int role, long summonerId, Observer<StatCollection> observer, Function<StatCollection, StatCollection> mapFunction) {
        Observable<StatCollection> statCollectionObservable = restApiExecutor.GetAnalysisStatCollection(role, summonerId);
        statCollectionObservable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(mapFunction)
                //.doOnError() TODO
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * Load the Analysis statistic cards for a summoner
     * @param role The role to filter for e.g TOP
     * @param summonerId The summoner to filter to.
     * @param callback The callback for the observables onSubscibe.
     */
    public void LoadStatAnalysisCardObjects(int role, long summonerId, Observer<StatCollection> callback) {
        Function<StatCollection, StatCollection> mappingFunction = collection -> {
            // TODO implement realm stuff here.
            return collection;
        };
        loadStatAnalysisCardObjects(role, summonerId, callback, mappingFunction);
    }

    /**
     * Load The stats that we need for the cards. This method is mainly used for testing the private version.
     * @param role The role to filter by E.g TOP.
     * @param summonerId The Summoner to filter by.
     * @param callback The callback for the observables subscribe.
     * @param mapFunction Map the data before saving it. Must return a statCollection. Used for caching.
     */
    public void LoadStatAnalysisCardObjects(int role, long summonerId, Observer<StatCollection> callback, Function<StatCollection, StatCollection> mapFunction) {
        loadStatAnalysisCardObjects(role, summonerId, callback, mapFunction);
    }

    /**
     * Load all the stat points for a single stat.
     * @param statId The stat we are interested in.
     * @param summonerId The summoner we are filtering by. Not really sure this is neccessary.
     * @param observer The callback.
     */
    public void LoadStatHistory(long statId, long summonerId, Observer<StatSummary> observer) {

    }
}
