package com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab.StatisticsTabContract;

/**
 * Created by Josiah Kendall
 */

public class StatisticsGameStageComparisonViewHolder
        extends RecyclerView.ViewHolder
        implements StatisticsTabContract.CardView {

    private  StatisticsTabContract.Presenter presenter;

    public StatisticsGameStageComparisonViewHolder(View itemView, StatisticsTabContract.Presenter presenter) {
        super(itemView);
        this.presenter = presenter;
    }
}