package com.teamunemployment.lolanalytics.front_page;

import com.teamunemployment.lolanalytics.io.RESTApiExecutor;
import com.teamunemployment.lolanalytics.io.RealmExecutor;
import com.teamunemployment.lolanalytics.data.model.DoubleWrapper;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * @author Josiah Kendall
 * Interactor for fetching
 */

public class BaseActivityPersistenceInteractor {

    // Our repositories.
    private RealmExecutor realmExecutor;
    private RESTApiExecutor restApiExecutor;

    @Inject
    public BaseActivityPersistenceInteractor(RESTApiExecutor restApiExecutor, RealmExecutor realmExecutor) {
        this.restApiExecutor = restApiExecutor;
        this.realmExecutor = realmExecutor;
    }

    public void fetchWinRateForRole(long summonerId, String role, final BaseActivityContract.Presenter presenter) {

        Observable<DoubleWrapper> winRateObsservable = restApiExecutor.GetWinRateForRole(summonerId, role);
        winRateObsservable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(doubleWrapper -> {
                    // realmExecutor. TODO - cache
                    return doubleWrapper;
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<DoubleWrapper>() {

            @Override
            public void onError(Throwable e) {
                presenter.handleError(e);
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DoubleWrapper doubleWrapper) {
                presenter.onWinRateLoaded(doubleWrapper.data);
            }

        });

    }
}
