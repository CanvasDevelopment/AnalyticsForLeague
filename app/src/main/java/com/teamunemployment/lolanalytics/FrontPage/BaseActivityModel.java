package com.teamunemployment.lolanalytics.FrontPage;

import com.teamunemployment.lolanalytics.Data.RESTApiExecutor;
import com.teamunemployment.lolanalytics.Data.RealmExecutor;
import com.teamunemployment.lolanalytics.Data.model.DoubleWrapper;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author Josiah Kendall
 */

public class BaseActivityModel {

    // Our repositories.

    private RealmExecutor realmExecutor;
    private RESTApiExecutor restApiExecutor;

    @Inject
    public BaseActivityModel(RESTApiExecutor restApiExecutor, RealmExecutor realmExecutor) {
        this.restApiExecutor = restApiExecutor;
        this.realmExecutor = realmExecutor;
    }

    public void fetchWinRateForRole(long summonerId, String role, final BaseActivityContract.Presenter presenter) {

        Observable<DoubleWrapper> winRateObsservable = restApiExecutor.GetWinRateForRole(summonerId, role);
        winRateObsservable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(new Func1<DoubleWrapper, DoubleWrapper>() {
                    @Override
                    public DoubleWrapper call(DoubleWrapper doubleWrapper) {
                       // realmExecutor. TODO - cache
                        return doubleWrapper;
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<DoubleWrapper>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                presenter.handleError(e);
            }

            @Override
            public void onNext(DoubleWrapper doubleWrapper) {
                presenter.onWinRateLoaded(doubleWrapper.data);
            }

        });

    }
}
