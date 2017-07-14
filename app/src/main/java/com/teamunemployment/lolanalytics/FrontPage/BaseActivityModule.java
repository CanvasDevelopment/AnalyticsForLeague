package com.teamunemployment.lolanalytics.FrontPage;

import com.teamunemployment.lolanalytics.Data.RESTApiExecutor;
import com.teamunemployment.lolanalytics.Data.RealmExecutor;

import dagger.Module;
import dagger.Provides;

/**
 * @author Josiah Kendall
 */
@Module
public class BaseActivityModule {

    @Provides
    BaseActivityPersistenceInteractor provideBaseActivityModel(RealmExecutor realmExecutor, RESTApiExecutor restApiExecutor) {
        return new BaseActivityPersistenceInteractor(restApiExecutor, realmExecutor);
    }

    @Provides
    BaseActivityPresenter provideBaseActivityPresenter(BaseActivityPersistenceInteractor baseActivityPersistenceInteractor) {
        return new BaseActivityPresenter(baseActivityPersistenceInteractor);
    }
}
