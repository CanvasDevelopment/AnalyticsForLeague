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
    BaseActivityModel provideBaseActivityModel(RealmExecutor realmExecutor, RESTApiExecutor restApiExecutor) {
        return new BaseActivityModel(restApiExecutor, realmExecutor);
    }

    @Provides
    BaseActivityPresenter provideBaseActivityPresenter(BaseActivityModel baseActivityModel) {
        return new BaseActivityPresenter(baseActivityModel);
    }
}
