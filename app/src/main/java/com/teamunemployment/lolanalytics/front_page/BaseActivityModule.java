package com.teamunemployment.lolanalytics.front_page;

import com.teamunemployment.lolanalytics.io.RESTApiExecutor;
import com.teamunemployment.lolanalytics.io.RealmExecutor;
import com.teamunemployment.lolanalytics.front_page.Search.SearchInteractor;
import com.teamunemployment.lolanalytics.front_page.Search.SearchPresenter;

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

    @Provides
    SearchPresenter provideSearchPresenter(SearchInteractor searchInteractor) {
        return new SearchPresenter(searchInteractor);
    }

    @Provides
    SearchInteractor provideSearchInteractor() {
        return new SearchInteractor();
    }
}
