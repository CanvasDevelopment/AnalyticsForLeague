package com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab;

import android.content.Context;

import com.teamunemployment.lolanalytics.Data.RESTApiExecutor;
import com.teamunemployment.lolanalytics.Data.RealmExecutor;

import dagger.Module;
import dagger.Provides;

/**
 * @author Josiah Kendall
 */
@Module
public class PlayerAnalysisModule {

    @Provides
    PlayerAnalysisPresenter providePlayerAnalysisBasePresenter(PlayerAnalysisPersistanceInteracter playerAnalysisPersistanceInteracter, Context context) {
        return new PlayerAnalysisPresenter(playerAnalysisPersistanceInteracter, context);
    }

    @Provides
    PlayerAnalysisPersistanceInteracter providePlayerAnalysisPersistanceInteracter(RESTApiExecutor restApiExecutor, RealmExecutor realmExecutor, Context context) {
        return new PlayerAnalysisPersistanceInteracter(restApiExecutor, realmExecutor, context);
    }
}
