package com.teamunemployment.lolanalytics.Login;

import com.teamunemployment.lolanalytics.Data.RESTApiExecutor;
import com.teamunemployment.lolanalytics.Data.RealmExecutor;
import com.teamunemployment.lolanalytics.Data.model.LongWrapper;

import javax.inject.Inject;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * @author Josiah Kendall
 */

public class LoginModel {

    private RESTApiExecutor restApiExecutor;
    private RealmExecutor realmExecutor;

    @Inject
    public LoginModel(RESTApiExecutor restApiExecutor, RealmExecutor realmExecutor) {
        this.restApiExecutor = restApiExecutor;
        this.realmExecutor = realmExecutor;
    }


    public void requestLoginAttempt(String summonerName, String region, final LoginContract.BasePresenter presenter) {
        Observable<LongWrapper> longWrapperObservable = restApiExecutor.GetSummonerId(summonerName, region);
        longWrapperObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LongWrapper>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LongWrapper value) {
                        presenter.setLoginResult(value.data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        presenter.handleError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void saveUserDetails(String username, long loginResult, String region) {

    }
}
