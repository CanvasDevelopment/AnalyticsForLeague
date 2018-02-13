package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab

import android.content.Context
import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.Utils.getMatchHistoryCardData
import com.teamunemployment.lolanalytics.Utils.getMatchIds
import com.teamunemployment.lolanalytics.data.model.Result

import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.MatchHistoryCardViewContract
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Model.MatchHistoryCardData
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import com.teamunemployment.lolanalytics.mock.MockHttpResponseInterceptor
import com.teamunemployment.lolanalytics.mock.MockMatchHistoryServiceResponses
import retrofit2.Call
import retrofit2.Response

/**
 * TODO refactor this class to not have hardcoded observers.
 * @author Josiah Kendall
 */
class MatchHistoryInteractor(private val context: Context,
                             private val retrofitFactory: RetrofitFactory,
                             private val network: Network) {

    private val mockResponses = MockMatchHistoryServiceResponses()

    private val mockInterceptor = MockHttpResponseInterceptor("200", 200)
    fun loadCachedMatchHistoryData(role: Int, summonerId: Long, presenter: MatchHistoryTabContract.Presenter) {
        //        // Create an observable for fetching our cached value, as we want to getMatchIds this off of the base thread.
        //        Observable<ArrayList<MatchIdWrapper>> observable = Observable.create((ObservableEmitter<ArrayList<MatchIdWrapper>> emitter) -> {
        //            Realm.init(context);
        //            Realm realm = Realm.getDefaultInstance();
        //            ArrayList<MatchIdWrapper> data = realmExecutor.LoadMatchList(realm, summonerId);
        //            emitter.onNext(data);
        //            emitter.onComplete();
        //        });
        //
        //        observable.subscribeOn(AndroidSchedulers.mainThread()) // Gotta be on the main thread for realm. If performance is an issue, use fetchAsync()
        //                .observeOn(AndroidSchedulers.mainThread())
        //                .subscribe(new Observer<ArrayList<MatchIdWrapper>>() {
        //                    @Override
        //                    public void onError(Throwable e) {
        //                        presenter.onError(e);
        //                    }
        //
        //                    @Override
        //                    public void onComplete() {
        //
        //                    }
        //
        //                    @Override
        //                    public void onSubscribe(Disposable d) {
        //
        //                    }
        //
        //                    @Override
        //                    public void onNext(ArrayList<MatchIdWrapper> matchHistoryData) {
        //                        presenter.onMatchListLoadedSuccessfully(matchHistoryData);
        //                    }
        //                });
    }

    /**
     * Fetch our match history value from the server.
     * @param role The role we want the value for.
     * @param summonerId .
     * @param presenter The presenter to return the value to.
     */
    fun loadFreshMatchHistoryData(role: Int, summonerId: Long, presenter: MatchHistoryPresenter) {
        //        if (!network.isConnectingToInternet(context)) {
        //            return;
        //        }
        //        Observable<MatchHistoryData> matchHistoryDataObservable = restApiExecutor.GetMatchListForSummonerInSpecificRole(summonerId, role);
        //        matchHistoryDataObservable
        //                .subscribeOn(Schedulers.io())
        //                .observeOn(Schedulers.computation())
        //                .map(matchHistoryData -> {
        //                    Realm.init(context);
        //                    Realm realm = Realm.getDefaultInstance();
        //                    realmExecutor.SaveMatchList(realm, matchHistoryData.getItems());
        //                    return matchHistoryData.getItems();
        //                })
        //                .observeOn(AndroidSchedulers.mainThread())
        //                .subscribe(new Observer<ArrayList<MatchIdWrapper>>() {
        //
        //                    @Override
        //                    public void onError(Throwable e) {
        //                        presenter.onError(e);
        //                    }
        //
        //                    @Override
        //                    public void onComplete() {
        //
        //                    }
        //
        //                    @Override
        //                    public void onSubscribe(Disposable d) {
        //
        //                    }
        //
        //                    @Override
        //                    public void onNext(ArrayList<MatchIdWrapper> matchHistoryData) {
        //                        presenter.onMatchListLoadedSuccessfully(matchHistoryData);
        //                    }
        //                });
    }

    //    // Think these two methods might be useless.
    //    public Observable<MatchSummary> LoadFreshMatchSummary(long matchId, MatchHistoryPresenter matchHistoryPresenter) {
    //        return null;
    //    }
    //
    //    public Observable<MatchSummary> LoadCachedMatchSummary(long matchId, MatchHistoryPresenter matchHistoryPresenter) {
    //        return null;
    //    }

    /**
     * Load the match details to show on a card.
     * @param id The id of the match as per saved in our database. may or may not be the same as the Riot match id.
     * @param presenter The presenter to return the value to when we are done loading.
     */
    fun loadMatchDetails(id: Long,
                         presenter : MatchHistoryTabContract.Presenter,
                         cardViewContract : MatchHistoryCardViewContract,
                         region: String,
                         summonerId: Long) {
        async {
            val url = network.getUrl(region)
            val matchHistoryService = retrofitFactory.produceMockRetrofitInterface(MatchHistoryService::class.java, mockInterceptor)
            mockInterceptor.content = mockResponses.getMatchSummary(id)
            val call: Call<Result<MatchHistoryCardData>> = matchHistoryService.fetchMatchSummary(id,summonerId)
            val response: Response<Result<MatchHistoryCardData>> = await { call.execute() }

            val result: Result<MatchHistoryCardData> = response.getMatchHistoryCardData()
            val code = result.resultCode
            presenter.setLoadedCardData(result.data, cardViewContract)
        }
    }

    fun loadMatches(region: String,
                    summonerId: Long,
                    role: Int,
                    numberOfMatches: Int,
                    presenter: MatchHistoryPresenter) {
        async {
            val url = network.getUrl(region)
            val matchHistoryService = retrofitFactory.produceMockRetrofitInterface(MatchHistoryService::class.java, mockInterceptor)
            mockInterceptor.content = mockResponses.getMatchList()
            val call: Call<Result<ArrayList<String>>> = matchHistoryService.fetchMatches(numberOfMatches, summonerId, role)
            val response: Response<Result<ArrayList<String>>> = await { call.execute() }

            val result: Result<ArrayList<String>> = response.getMatchIds()
            val code = result.resultCode // do something here?
            presenter.onMatchListLoadedSuccessfully(result.data)
        }
    }

    private val matchList: String = "{" +
            "data : [1,2,3,4,5,6,7,8,9}"


    /**
     * THis should only ever be fetched once, as match details should never change.
     * @param id
     * @param presenter
     * @param cardViewContract
     */
    private fun fetchFreshMatchDetails(id: Long, presenter: MatchHistoryTabContract.Presenter, cardViewContract: MatchHistoryCardViewContract) {
        //        Observable<MatchHistoryCardData> matchHistoryCardDataObservable = restApiExecutor.GetMatchHistoryCardData(id, -1); // TODO
        //        matchHistoryCardDataObservable
        //                .subscribeOn(Schedulers.newThread())
        //                .observeOn(Schedulers.computation())
        //                .map(matchHistoryCardData -> {
        //                    Realm.init(context);
        //                    Realm realm = Realm.getDefaultInstance();
        //
        //                    matchHistoryCardData.setMatchId(id);
        //                    realmExecutor.SaveMatchHistoryCardData(realm, matchHistoryCardData);
        //                    return matchHistoryCardData;
        //                })
        //                .observeOn(AndroidSchedulers.mainThread())
        //                .subscribe(new Observer<MatchHistoryCardData>() {
        //            @Override
        //            public void onSubscribe(Disposable d) {
        //
        //            }
        //
        //            @Override
        //            public void onNext(MatchHistoryCardData value) {
        //                presenter.setLoadedCardData(value, cardViewContract);
        //            }
        //
        //            @Override
        //            public void onError(Throwable e) {
        //                // TODO
        //                presenter.handleError(e);
        //            }
        //
        //            @Override
        //            public void onComplete() {
        //
        //            }
        //        });
    }
}
