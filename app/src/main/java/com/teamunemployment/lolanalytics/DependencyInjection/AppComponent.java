package com.teamunemployment.lolanalytics.DependencyInjection;

import com.teamunemployment.lolanalytics.Jungle.ViewFragment;
import com.teamunemployment.lolanalytics.Base.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Josiah Kendall.
 *
 * Inject our components.
 */

@Singleton
@Component(modules = {AppModule.class, ComponentModule.class})
public interface AppComponent {
    void InjectJungleView(ViewFragment jungleView);
    void InjectBaseView(BaseFragment baseFragment);
}
