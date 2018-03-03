package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model;

import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.recycler.AnalyseAdapter;
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.AnalyseInteractor;
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.AnalysePresenter;
import com.teamunemployment.lolanalytics.Utils.RoleUtils;

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

//    @Provides
//    AnalyseInteractor provideAnalyseInteractor() {
//        return new AnalyseInteractor();
//    }
}
