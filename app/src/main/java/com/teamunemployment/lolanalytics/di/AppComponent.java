package com.teamunemployment.lolanalytics.di;

import com.teamunemployment.lolanalytics.front_page.BaseActivityModule;
import com.teamunemployment.lolanalytics.front_page.BaseActivityView;
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.AnalyseTabView;
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.AnalyseTabModule;
import com.teamunemployment.lolanalytics.front_page.Tabs.CoachTab.CoachTabModule;
import com.teamunemployment.lolanalytics.front_page.Tabs.CoachTab.CoachView;
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Dependencies.MatchHistoryModule;
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.MatchHistoryTabView;
import com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab.PlayerAnalysisModule;
import com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab.PlayerAnalysisView;
import com.teamunemployment.lolanalytics.login.sign_in.ToDelete;
import com.teamunemployment.lolanalytics.login.sign_in.LoginView;
import com.teamunemployment.lolanalytics.front_page.Tabs.StatsComparisonTab.StatisticsTabModule;
import com.teamunemployment.lolanalytics.front_page.Tabs.StatsComparisonTab.TabView;
import com.teamunemployment.lolanalytics.io.di.IoModuleDagger2;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Josiah Kendall.
 */
@Singleton
@Component(modules = {AppModule.class, StatisticsTabModule.class, ToDelete.class,
        BaseActivityModule.class, MatchHistoryModule.class, PlayerAnalysisModule.class,
        CoachTabModule.class, AnalyseTabModule.class, IoModuleDagger2.class})
public interface AppComponent {
    void InjectView(TabView tabView);
    void InjectView(LoginView loginView);
    void InjectView(BaseActivityView baseActivityView);
    void InjectView(MatchHistoryTabView matchHistoryTabView);
    void InjectView(PlayerAnalysisView playerAnalysisView);
    void InjectView(CoachView coachView);
    void InjectView(AnalyseTabView analyseTabView);
}
