package com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab;

import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab.model.StatisticsGameStageComparisonViewHolder;

/**
 * Created by Josiah Kendall
 */

public interface StatisticsTabContract {

    interface View {

    }

    interface Presenter {

        void onCardBinding(StatisticsGameStageComparisonViewHolder holder, int position);
        int getFilterListSize();
        void setView(View view);
    }

    interface CardView {

    }

    interface Adapter {
        void setPresenter(Presenter presenter);
    }
}
