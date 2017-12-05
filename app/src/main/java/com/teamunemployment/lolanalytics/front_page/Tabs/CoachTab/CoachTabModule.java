package com.teamunemployment.lolanalytics.front_page.Tabs.CoachTab;

import android.content.Context;

import dagger.Provides;
import dagger.Module;

/**
 * @author Josiah Kendall
 */

@Module

public class CoachTabModule {

    @Provides
    CoachPresenter provideCoachPresenter(CoachInteractor coachInteractor, Context context,
                                         CoachHorizontalScrollerAdapter coachHorizontalScrollerAdapter) {
        return new CoachPresenter(coachInteractor, context, coachHorizontalScrollerAdapter);
    }

    @Provides
    CoachInteractor provideCoachInteractor(Context context) {
        return new CoachInteractor(context);
    }

    @Provides
    CoachHorizontalScrollerAdapter provideCoachHorizontalScrollerAdapter(Context context) {
        return new CoachHorizontalScrollerAdapter(context);
    }

}
