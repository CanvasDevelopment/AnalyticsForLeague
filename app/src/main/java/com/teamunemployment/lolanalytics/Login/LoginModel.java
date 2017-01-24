package com.teamunemployment.lolanalytics.Login;

import com.teamunemployment.lolanalytics.Data.RESTApiExecutor;
import com.teamunemployment.lolanalytics.Data.RealmExecutor;
import com.teamunemployment.lolanalytics.Data.model.Data;
import com.teamunemployment.lolanalytics.Data.model.LongWrapper;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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


    public void requestLoginAttempt(String summonerName, String region, final LoginContract.Presenter presenter) {
        Observable<LongWrapper> longWrapperObservable = restApiExecutor.GetSummonerId(summonerName, region);
        longWrapperObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LongWrapper>() {

                    @Override
                    public void onCompleted() {
                        // not needed.
                    }

                    @Override
                    public void onError(Throwable e) {
                        presenter.setLoginResult(new Long(-1));
                    }

                    @Override
                    public void onNext(LongWrapper longWrapper) {
                        presenter.setLoginResult(longWrapper.data);
                    }
                });

    }

    public void saveUserDetails(String username, long loginResult, String region) {

    }
}
