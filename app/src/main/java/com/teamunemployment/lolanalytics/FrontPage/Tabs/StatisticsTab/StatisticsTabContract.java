package com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab;

import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab.model.GameStageStatisticModel;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab.model.ThreeGameStageStatistic;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab.model.StatisticsGameStageComparisonViewHolder;

import java.util.ArrayList;

/**
 * Created by Josiah Kendall
 */

public interface StatisticsTabContract {

    interface View {
        void setAdapter(Adapter adapter);
        void setNoDataPlaceholderVisible();
        void setNoDataPlaceholderHidden();
        void setListVisible();
        void setListHidden();
        void setPlaceholderString(String s);
    }

    interface Presenter {

        void onCardBinding(StatisticsGameStageComparisonViewHolder holder, int position);
        int getFilterListSize();
        void setView(View view);
        void start();
        void refreshData();
        void handleDataResponse(ArrayList<ThreeGameStageStatistic> data);
    }

    interface CardView {
        void setTitle(String title);
        void setEarlyGame(GameStageStatisticModel earlyGame);
        void setMidGame(GameStageStatisticModel midGame);
        void setLateGame(GameStageStatisticModel lateGame);
    }

    interface Adapter {
        void setPresenter(Presenter presenter);
    }
}
