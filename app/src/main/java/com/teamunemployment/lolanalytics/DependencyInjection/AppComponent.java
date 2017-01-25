package com.teamunemployment.lolanalytics.DependencyInjection;

import com.teamunemployment.lolanalytics.FrontPage.BaseActivityModule;
import com.teamunemployment.lolanalytics.FrontPage.BaseActivityView;
import com.teamunemployment.lolanalytics.Login.LoginContract;
import com.teamunemployment.lolanalytics.Login.LoginModule;
import com.teamunemployment.lolanalytics.Login.LoginView;
import com.teamunemployment.lolanalytics.FrontPage.StatsComparisonTab.StatisticsTabModule;
import com.teamunemployment.lolanalytics.FrontPage.StatsComparisonTab.TabView;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Josiah Kendall.
 */
@Singleton
@Component(modules = {AppModule.class, StatisticsTabModule.class, LoginModule.class, BaseActivityModule.class})
public interface AppComponent {
    void InjectView(TabView tabView);
    void InjectView(LoginView loginView);
    void InjectView(BaseActivityView baseActivityView);

}
