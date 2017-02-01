package com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab;

import android.content.Context;

import com.teamunemployment.lolanalytics.Data.RESTApiExecutor;
import com.teamunemployment.lolanalytics.Data.RealmExecutor;
import com.teamunemployment.lolanalytics.Data.model.Data;
import com.teamunemployment.lolanalytics.Data.model.MatchHistoryData;
import com.teamunemployment.lolanalytics.Data.model.MatchSummary;

import io.realm.Realm;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author Josiah Kendall
 */
public class MatchHistoryModel {

    private RESTApiExecutor restApiExecutor;
    private RealmExecutor realmExecutor;
    private Context context;

    public MatchHistoryModel(RESTApiExecutor restApiExecutor, RealmExecutor realmExecutor,
                             Context context) {
        this.restApiExecutor = restApiExecutor;
        this.realmExecutor = realmExecutor;
        this.context = context;
    }

    public void LoadCachedMatchHistoryData(final int role, final long summonerId, final MatchHistoryTabContract.Presenter presenter) {
        // Create an observable for fetching our cached data, as we want to get this off of the base thread.
        Observable<MatchHistoryData> observable = Observable.create(new Observable.OnSubscribe<MatchHistoryData>() {
            @Override
            public void call(Subscriber<? super MatchHistoryData> subscriber) {
                Realm.init(context);
                Realm realm = Realm.getDefaultInstance();
                MatchHistoryData data = realmExecutor.FindMatchHistory(role, summonerId, realm);
                subscriber.onNext(data);
                subscriber.onCompleted();
            }
        });

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MatchHistoryData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        presenter.onError(e);
                    }

                    @Override
                    public void onNext(MatchHistoryData matchHistoryData) {
                        presenter.onMatchListLoadedSuccessfully(matchHistoryData);
                    }
                });
    }

    public void LoadFreshMatchHistoryData(int role, long summonerId, final MatchHistoryPresenter presenter) {
        Observable<MatchHistoryData> matchHistoryDataObservable = restApiExecutor.GetMatchListForSummonerInSpecificRole(summonerId, role);
        matchHistoryDataObservable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(new Func1<MatchHistoryData, MatchHistoryData>() {
                    @Override
                    public MatchHistoryData call(MatchHistoryData matchHistoryData) {
                        Realm.init(context);
                        Realm realm = Realm.getDefaultInstance();
                        realmExecutor.SaveMatchHistory(realm, matchHistoryData);
                        // Issue - cant close realm here, because we need to use matchHistoryData.
                        return matchHistoryData;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MatchHistoryData>() {
                    @Override
                    public void onCompleted() {
                        // Not required.
                    }

                    @Override
                    public void onError(Throwable e) {
                        presenter.onError(e);
                    }

                    @Override
                    public void onNext(MatchHistoryData matchHistoryData) {
                        presenter.onMatchListLoadedSuccessfully(matchHistoryData);
                    }
                });
    }

    public Observable<MatchSummary> LoadFreshMatchSummary(long matchId, MatchHistoryPresenter matchHistoryPresenter) {
        return null;
    }

    public Observable<MatchSummary> LoadCachedMatchSummary(long matchId, MatchHistoryPresenter matchHistoryPresenter) {
        return null;
    }
}
