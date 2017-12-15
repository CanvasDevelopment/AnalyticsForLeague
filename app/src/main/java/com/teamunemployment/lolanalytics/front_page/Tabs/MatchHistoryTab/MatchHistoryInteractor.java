package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab;

import android.content.Context;

import com.teamunemployment.lolanalytics.io.RESTApiExecutor;
import com.teamunemployment.lolanalytics.io.RealmExecutor;
import com.teamunemployment.lolanalytics.data.model.MatchHistoryData;
import com.teamunemployment.lolanalytics.data.model.MatchIdWrapper;
import com.teamunemployment.lolanalytics.data.model.MatchSummary;
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.MatchHistoryCardViewContract;
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Model.MatchHistoryCardData;
import com.teamunemployment.lolanalytics.Utils.Network;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

/**
 * TODO refactor this class to not have hardcoded observers.
 * @author Josiah Kendall
 */
public class MatchHistoryInteractor {
//    Network network = new Network();

    private RESTApiExecutor restApiExecutor;
    private RealmExecutor realmExecutor;
    private Context context;

    public MatchHistoryInteractor(RESTApiExecutor restApiExecutor, RealmExecutor realmExecutor,
                                  Context context) {
        this.restApiExecutor = restApiExecutor;
        this.realmExecutor = realmExecutor;
        this.context = context;
    }

    public void LoadCachedMatchHistoryData(final int role, final long summonerId, final MatchHistoryTabContract.Presenter presenter) {
        // Create an observable for fetching our cached value, as we want to get this off of the base thread.
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

    /**
     * Fetch our match history value from the server.
     * @param role The role we want the value for.
     * @param summonerId .
     * @param presenter The presenter to return the value to.
     */
    public void LoadFreshMatchHistoryData(int role, long summonerId, final MatchHistoryPresenter presenter) {
//        if (!network.isConnectingToInternet(context)) {
//            return;
//        }
        Observable<MatchHistoryData> matchHistoryDataObservable = restApiExecutor.GetMatchListForSummonerInSpecificRole(summonerId, role);
        matchHistoryDataObservable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(matchHistoryData -> {
                    Realm.init(context);
                    Realm realm = Realm.getDefaultInstance();
                    realmExecutor.SaveMatchList(realm, matchHistoryData.getItems());
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
    public Observable<MatchSummary> LoadFreshMatchSummary(long matchId, MatchHistoryPresenter matchHistoryPresenter) {
        return null;
    }

    public Observable<MatchSummary> LoadCachedMatchSummary(long matchId, MatchHistoryPresenter matchHistoryPresenter) {
        return null;
    }

    /**
     * Load the match details to show on a card.
     * @param id The id of the match as per saved in our database. may or may not be the same as the Riot match id.
     * @param presenter The presenter to return the value to when we are done loading.
     */
    public void LoadMatchDetails(long id,
                                 MatchHistoryTabContract.Presenter presenter,
                                 MatchHistoryCardViewContract cardViewContract) {
        // Fetch from the cache.
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        MatchHistoryCardData matchHistoryCardData = realmExecutor.GetSingleMatchHistoryCard(realm, id);
        if (matchHistoryCardData != null) {
            cardViewContract.setChampName(matchHistoryCardData.getChampName());
            cardViewContract.setGraph1(matchHistoryCardData.getKills());
            cardViewContract.setGraph2(matchHistoryCardData.getDeaths());
            cardViewContract.setGraph3(matchHistoryCardData.getCsTotal());
            cardViewContract.setKDA("3/2/5"); // TODO
        } else {
            // If there is nothing there, fetch from the server.
//            if (network.isConnectingToInternet(context)) {
                fetchFreshMatchDetails(id, presenter, cardViewContract);
//            }
        }
    }

    /**
     * THis should only ever be fetched once, as match details should never change.
     * @param id
     * @param presenter
     * @param cardViewContract
     */
    private void fetchFreshMatchDetails(final long id, MatchHistoryTabContract.Presenter presenter, MatchHistoryCardViewContract cardViewContract) {
        Observable<MatchHistoryCardData> matchHistoryCardDataObservable = restApiExecutor.GetMatchHistoryCardData(id, -1); // TODO
        matchHistoryCardDataObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.computation())
                .map(matchHistoryCardData -> {
                    Realm.init(context);
                    Realm realm = Realm.getDefaultInstance();

                    matchHistoryCardData.setMatchId(id);
                    realmExecutor.SaveMatchHistoryCardData(realm, matchHistoryCardData);
                    return matchHistoryCardData;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MatchHistoryCardData>() {
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
                presenter.handleError(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
