package com.teamunemployment.lolanalytics.DependencyInjection;

import com.teamunemployment.lolanalytics.Jungle.JungleView;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Josiah Kendall.
 */

@Singleton
@Component(modules = {AppModule.class, ComponentModule.class})
public interface AppComponent {
    void InjectJungleView(JungleView jungleView);
}
