package com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab.model.StatisticsCardDataObject;
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
        void handleDataResponse(ArrayList<StatisticsCardDataObject> data);
        PieChart prepClickableDetailChart(PieChart pieChart);
    }

    interface CardView {
        void setTitle(String title);
        void setEarlyGame(PieData data, String centerText);
        void setMidGame(PieData data, String centerText);
        void setLateGame(PieData data, String centerText);
    }

    interface Adapter {
        void setPresenter(Presenter presenter);
    }
}
