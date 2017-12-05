package com.teamunemployment.lolanalytics.front_page.Tabs.StatisticsTab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.teamunemployment.lolanalytics.front_page.Tabs.StatisticsTab.model.StatisticsGameStageComparisonViewHolder;
import com.teamunemployment.lolanalytics.R;

/**
 * Created by Josiah Kendall
 */

public class StatisticsTabAdapter
        extends RecyclerView.Adapter<StatisticsGameStageComparisonViewHolder>
        implements StatisticsTabContract.Adapter{

    private StatisticsTabContract.Presenter presenter;

    @Override
    public StatisticsGameStageComparisonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Create a new view holder by inflating a view and giving it our presenter to present from
        return new StatisticsGameStageComparisonViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.analyse_card_item, parent, false),
                presenter
        );
    }

    @Override
    public void onBindViewHolder(StatisticsGameStageComparisonViewHolder holder, int position) {
        presenter.onCardBinding(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getFilterListSize();
    }

    @Override
    public void setPresenter(StatisticsTabContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
