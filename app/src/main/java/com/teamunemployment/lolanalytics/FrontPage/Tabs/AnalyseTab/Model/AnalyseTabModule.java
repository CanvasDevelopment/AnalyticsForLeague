package com.teamunemployment.lolanalytics.FrontPage.Tabs.AnalyseTab.Model;

import com.teamunemployment.lolanalytics.FrontPage.Tabs.AnalyseTab.AnalyseAdapter;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.AnalyseTab.AnalyseInteractor;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.AnalyseTab.AnalysePresenter;
import com.teamunemployment.lolanalytics.Utils.RoleUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Josiah Kendall
 */

@Module
public class AnalyseTabModule {

    @Provides
    AnalysePresenter provideAnalysePresenter(AnalyseInteractor analyseInteractor, AnalyseAdapter analyseAdapter, RoleUtils roleUtils) {
        return new AnalysePresenter(analyseInteractor, analyseAdapter, roleUtils);
    }

    @Provides
    AnalyseAdapter provideAnalyseAdapter() {
        return new AnalyseAdapter();
    }

    @Provides
    RoleUtils provideRoleUtils() {
        return new RoleUtils();
    }

    @Provides
    AnalyseInteractor provideAnalyseInteractor() {
        return new AnalyseInteractor();
    }
}
