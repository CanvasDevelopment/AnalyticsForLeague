package com.teamunemployment.lolanalytics.DependencyInjection;

import com.teamunemployment.lolanalytics.FrontPage.BaseActivityModule;
import com.teamunemployment.lolanalytics.FrontPage.BaseActivityView;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.MatchHistoryModule;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.MatchHistoryTabView;
import com.teamunemployment.lolanalytics.Login.LoginModule;
import com.teamunemployment.lolanalytics.Login.LoginView;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.StatisticsTabModule;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.TabView;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Josiah Kendall.
 */
@Singleton
@Component(modules = {AppModule.class, StatisticsTabModule.class, LoginModule.class, BaseActivityModule.class, MatchHistoryModule.class})
public interface AppComponent {
    void InjectView(TabView tabView);
    void InjectView(LoginView loginView);
    void InjectView(BaseActivityView baseActivityView);
    void InjectView(MatchHistoryTabView matchHistoryTabView);
}
