package com.teamunemployment.lolanalytics.FrontPage;

import com.teamunemployment.lolanalytics.Data.RESTApiExecutor;
import com.teamunemployment.lolanalytics.Data.RealmExecutor;
import com.teamunemployment.lolanalytics.Data.model.DoubleWrapper;

import javax.inject.Inject;

import rx.Observable;

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

    public void fetchWinRateForRole(long summonerId, int role, BaseActivityContract.Presenter presenter) {

        //Observable<DoubleWrapper> winRateObsservable = restApiExecutor.GetWinRateForRole(summonerId, role);

    }
}
