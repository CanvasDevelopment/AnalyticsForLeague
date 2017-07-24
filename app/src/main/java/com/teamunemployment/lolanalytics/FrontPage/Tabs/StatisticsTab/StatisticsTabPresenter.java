package com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab;

import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab.model.StatisticsGameStageComparisonViewHolder;

import java.util.ArrayList;

/**
 * Created by Josiah Kendall
 */

public class StatisticsTabPresenter implements StatisticsTabContract.Presenter {

    private StatisticsTabAdapter            adapter;
    private StatisticsTabInteractor         interactor;
    private StatisticsTabContract.View      view;
    private ArrayList                       listData;
    public StatisticsTabPresenter(
            StatisticsTabInteractor statisticsTabInteractor,
            StatisticsTabAdapter statisticsTabAdapter) {

    }

    @Override
    public void onCardBinding(StatisticsGameStageComparisonViewHolder holder, int position) {
        // handle binding of the card
    }

    @Override
    public int getFilterListSize() {
        return listData.size();
    }

    @Override
    public void setView(StatisticsTabContract.View view) {

    }
}
