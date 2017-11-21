package com.teamunemployment.lolanalytics.DependencyInjection;

import com.teamunemployment.lolanalytics.FrontPage.BaseActivityModule;
import com.teamunemployment.lolanalytics.FrontPage.BaseActivityView;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.AnalyseTab.AnalyseTabView;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.AnalyseTab.Model.AnalyseTabModule;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.CoachTab.CoachTabModule;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.CoachTab.CoachView;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.Dependencies.MatchHistoryModule;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.MatchHistoryTabView;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.PlayerAnalysisModule;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.PlayerAnalysisView;
import com.teamunemployment.lolanalytics.login.LoginModule;
import com.teamunemployment.lolanalytics.login.LoginView;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.StatisticsTabModule;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.TabView;
import com.teamunemployment.lolanalytics.io.di.IoModuleDagger2;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Josiah Kendall.
 */
@Singleton
@Component(modules = {AppModule.class, StatisticsTabModule.class, LoginModule.class,
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
