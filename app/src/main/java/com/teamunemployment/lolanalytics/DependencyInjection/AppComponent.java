package com.teamunemployment.lolanalytics.DependencyInjection;

import com.teamunemployment.lolanalytics.Login.LoginContract;
import com.teamunemployment.lolanalytics.Login.LoginModule;
import com.teamunemployment.lolanalytics.StatsComparisonTab.StatisticsTabModule;
import com.teamunemployment.lolanalytics.StatsComparisonTab.TabView;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Josiah Kendall.
 */
@Singleton
@Component(modules = {AppModule.class, StatisticsTabModule.class})
public interface AppComponent {
    void InjectView(TabView jungleTabView);

}
