package com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.Dependencies;

import android.content.Context;

import com.teamunemployment.lolanalytics.io.RESTApiExecutor;
import com.teamunemployment.lolanalytics.io.RealmExecutor;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.MatchHistoryInteractor;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.MatchHistoryPresenter;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.BarChartModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Josiah Kendall
 */

@Module

public class MatchHistoryModule {
    @Provides
    MatchHistoryPresenter provideMatchHistoryBasePresenter(MatchHistoryInteractor matchHistoryInteractor, BarChartModel barChartModel) {
        return new MatchHistoryPresenter(matchHistoryInteractor, barChartModel);
    }
    // Need context for static Realm initialisation
    @Provides
    MatchHistoryInteractor provideMatchHistoryModel(RESTApiExecutor restApiExecutor, RealmExecutor realmExecutor, Context context) {
        return new MatchHistoryInteractor(restApiExecutor, realmExecutor, context);
    }
}
