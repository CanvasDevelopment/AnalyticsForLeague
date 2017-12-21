package com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab;

import android.content.Context;

import com.teamunemployment.lolanalytics.io.RESTApiExecutor;
import com.teamunemployment.lolanalytics.io.RealmExecutor;

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

//    @Provides
//    PlayerAnalysisPersistanceInteracter providePlayerAnalysisPersistanceInteracter(RESTApiExecutor restApiExecutor, RealmExecutor realmExecutor, Context context) {
//        return new PlayerAnalysisPersistanceInteracter(restApiExecutor, realmExecutor, context);
//    }
}
