package com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab;

import android.content.Context;

import com.teamunemployment.lolanalytics.Data.RESTApiExecutor;
import com.teamunemployment.lolanalytics.Data.RealmExecutor;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.BarChartModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Josiah Kendall
 */

@Module
public class MatchHistoryModule {
    @Provides
    MatchHistoryBasePresenter provideMatchHistoryBasePresenter(MatchHistoryInteractor matchHistoryInteractor, BarChartModel barChartModel) {
        return new MatchHistoryBasePresenter(matchHistoryInteractor, barChartModel);
    }
    // Need context for static Realm initialisation
    @Provides
    MatchHistoryInteractor provideMatchHistoryModel(RESTApiExecutor restApiExecutor, RealmExecutor realmExecutor, Context context) {
        return new MatchHistoryInteractor(restApiExecutor, realmExecutor, context);
    }
}
