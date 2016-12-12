package com.teamunemployment.lolanalytics.DependencyInjection;

import com.teamunemployment.lolanalytics.Jungle.Model.BarChartFactory;
import com.teamunemployment.lolanalytics.Jungle.Model.JungleModel;
import com.teamunemployment.lolanalytics.Jungle.PresentationLayer.JunglePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Josiah Kendall
 */

@Module
public class ComponentModule {

    @Provides
    BarChartFactory provideBarChartFactory() {
        return new BarChartFactory();
    }

    @Provides
    JungleModel provideJungleModel() {
        return new JungleModel();
    }

    @Provides
    JunglePresenter provideJunglePresenter(JungleModel jungleModel) {
        return new JunglePresenter(jungleModel);
    }
}
