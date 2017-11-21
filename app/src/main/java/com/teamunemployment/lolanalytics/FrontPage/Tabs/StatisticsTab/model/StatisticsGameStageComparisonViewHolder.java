package com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab.StatisticsTabContract;
import com.teamunemployment.lolanalytics.R;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Josiah Kendall
 */

public class StatisticsGameStageComparisonViewHolder
        extends RecyclerView.ViewHolder
        implements StatisticsTabContract.CardView {

    private StatisticsTabContract.Presenter presenter;

    @BindView(R.id.chart_1) PieChart chart1;
    @BindView(R.id.chart_2) PieChart chart2;
    @BindView(R.id.chart_3) PieChart chart3;
    @BindView(R.id.stat_card_title) TextView title;

    public StatisticsGameStageComparisonViewHolder(View itemView, StatisticsTabContract.Presenter presenter) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.presenter = presenter;
    }

    @Override
    public void setTitle(String titleString) {
        this.title.setText(titleString);
    }

    @Override
    public void setEarlyGame(PieData data, String centerText) {
        chart1 = presenter.prepClickableDetailChart(chart1);
        chart1.setCenterText(centerText);
        chart1.setData(data);
        chart1.invalidate();
    }

    @Override
    public void setMidGame(PieData data, String centerText) {
        chart2 = presenter.prepClickableDetailChart(chart1);
        chart2.setCenterText(centerText);
        chart2.setData(data);
        chart2.invalidate();
    }

    @Override
    public void setLateGame(PieData data, String centerText) {
        chart3 = presenter.prepClickableDetailChart(chart1);
        chart3.setCenterText(centerText);
        chart3.setData(data);
        chart3.invalidate();
    }


}
