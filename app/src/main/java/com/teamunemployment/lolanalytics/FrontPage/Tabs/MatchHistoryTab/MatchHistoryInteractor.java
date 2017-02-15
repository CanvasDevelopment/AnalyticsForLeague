package com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab;

import android.content.Context;

import com.teamunemployment.lolanalytics.Data.RESTApiExecutor;
import com.teamunemployment.lolanalytics.Data.RealmExecutor;
import com.teamunemployment.lolanalytics.Data.model.LongWrapper;
import com.teamunemployment.lolanalytics.Data.model.MatchHistoryData;
import com.teamunemployment.lolanalytics.Data.model.MatchIdWrapper;
import com.teamunemployment.lolanalytics.Data.model.MatchSummary;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.Model.MatchHistoryCardData;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

/**
 * TODO refactor this class to not have hardcoded observers. TDD to ensure complete.
 * @author Josiah Kendall
 */
public class MatchHistoryInteractor {

    private RESTApiExecutor restApiExecutor;
    private RealmExecutor realmExecutor;
    private Context context;

    public MatchHistoryInteractor(RESTApiExecutor restApiExecutor, RealmExecutor realmExecutor,
                                  Context context) {
        this.restApiExecutor = restApiExecutor;
        this.realmExecutor = realmExecutor;
        this.context = context;
    }

    public void LoadCachedMatchHistoryData(final int role, final long summonerId, final MatchHistoryTabContract.BasePresenter presenter) {
        // Create an observable for fetching our cached data, as we want to get this off of the base thread.
        Observable<ArrayList<MatchIdWrapper>> observable = Observable.create((ObservableEmitter<ArrayList<MatchIdWrapper>> emitter) -> {
            Realm.init(context);
            Realm realm = Realm.getDefaultInstance();
            ArrayList<MatchIdWrapper> data = realmExecutor.LoadMatchList(realm, summonerId);
            emitter.onNext(data);
            emitter.onComplete();
        });

        observable.subscribeOn(AndroidSchedulers.mainThread()) // Gotta be on the main thread for realm. If performance is an issue, use fetchAsync()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<MatchIdWrapper>>() {
                    @Override
                    public void onError(Throwable e) {
                        presenter.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<MatchIdWrapper> matchHistoryData) {
                        presenter.onMatchListLoadedSuccessfully(matchHistoryData);
                    }
                });
    }

    public void LoadFreshMatchHistoryData(int role, long summonerId, final MatchHistoryBasePresenter presenter) {
        Observable<MatchHistoryData> matchHistoryDataObservable = restApiExecutor.GetMatchListForSummonerInSpecificRole(summonerId, role);
        matchHistoryDataObservable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(matchHistoryData -> {
                    Realm.init(context);
                    Realm realm = Realm.getDefaultInstance();
                    realmExecutor.SaveMatchList(realm, matchHistoryData.getItems());
                    // Issue - cant close realm here, because we need to use matchHistoryData.
                    return matchHistoryData.getItems();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<MatchIdWrapper>>() {

                    @Override
                    public void onError(Throwable e) {
                        presenter.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<MatchIdWrapper> matchHistoryData) {
                        presenter.onMatchListLoadedSuccessfully(matchHistoryData);
                    }
                });
    }

    // Think these two methods might be useless.
    public Observable<MatchSummary> LoadFreshMatchSummary(long matchId, MatchHistoryBasePresenter matchHistoryPresenter) {
        return null;
    }

    public Observable<MatchSummary> LoadCachedMatchSummary(long matchId, MatchHistoryBasePresenter matchHistoryPresenter) {
        return null;
    }

    /**
     * Load the match details to show on a card.
     * @param id The id of the match as per saved in our database. may or may not be the same as the Riot match id.
     * @param presenter The presenter to return the data to when we are done loading.
     */
    public void LoadMatchDetails(long id, MatchHistoryTabContract.BasePresenter presenter, MatchHistoryCardViewContract cardViewContract) {
        Observable<MatchHistoryCardData> matchHistoryCardDataObservable = restApiExecutor.GetMatchHistoryCardData(id, -1); // TODO
        matchHistoryCardDataObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.computation())
                .map(matchHistoryCardData -> {
//            Realm.init(context);
//            Realm realm = Realm.getDefaultInstance(); TODO
//            //realmExecutor.SaveMatchList(realm, matchHistoryData);
//            // Issue - cant close realm here, because we need to use matchHistoryData.
            return matchHistoryCardData;
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MatchHistoryCardData>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MatchHistoryCardData value) {
                presenter.SetLoadedCardData(value, cardViewContract);
            }

            @Override
            public void onError(Throwable e) {
                // TODO
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
