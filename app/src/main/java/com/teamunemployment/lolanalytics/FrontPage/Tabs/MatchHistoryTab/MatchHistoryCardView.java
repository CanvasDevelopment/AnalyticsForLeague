package com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.Model.MatchHistoryCardData;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.BarChartModel;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.CardData;
import com.teamunemployment.lolanalytics.R;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Josiah Kendall.
 */

public class MatchHistoryCardView extends RecyclerView.ViewHolder implements MatchHistoryCardViewContract {

    private Context context;
    private View cardBase;
    private BarChartModel barChartModel;

    @Bind(R.id.champ_name) TextView champNameTextView;
    @Bind(R.id.chart1) BarChart barChart1;
    @Bind(R.id.chart2) BarChart barChart2;
    @Bind(R.id.chart3) BarChart barChart3;
    @Bind(R.id.chart4) BarChart barChart4;

    private MatchHistoryBasePresenter matchHistoryBasePresenter;

    public MatchHistoryCardView(View itemView, Context context, MatchHistoryBasePresenter matchHistoryBasePresenter, BarChartModel barChartModel) {
        super(itemView);
        this.cardBase = itemView;
        this.context = context;
        ButterKnife.bind(this, itemView);
        this.matchHistoryBasePresenter = matchHistoryBasePresenter;
        this.barChartModel = barChartModel;
    }

    public void loadData(long matchId) {
        matchHistoryBasePresenter.LoadCardData(matchId, this);
    }

    @Override
    public void setGraph1(CardData cardData) {
        setUpBarChart(barChart1, cardData);
    }

    @Override
    public void setGraph2(CardData cardData) {
        setUpBarChart(barChart2, cardData);

    }

    @Override
    public void setGraph3(CardData cardData) {
        setUpBarChart(barChart3, cardData);

    }

    @Override
    public void setGraph4(CardData cardData) {
        setUpBarChart(barChart4, cardData);

    }

    @Override
    public void setChampName(String champName) {
        champNameTextView.setText(champName);
    }

    @Override
    public void setChampIcon(String champIconUrl) {

    }

    @Override
    public void setKDA(String kdaString) {

    }

    // This is reused code from somewhere else - need to sort this out.
    private void setUpBarChart(BarChart barChart, CardData cardData) {
        BarDataSet barDataSet = barChartModel.FetchBarDataSet(cardData);
        barDataSet.setValueTextColor(context.getResources().getColor(R.color.grey));
        barDataSet.setColors(new int[]{R.color.teal, R.color.pink}, context); // Would be real cool to not use context here if possible.

        BarData barData = barChartModel.FetchBarData(barDataSet);
        barChart.setData(barData);

        // Below here is just setting the appearance of the graphs - no logic
        // X axis - horizontal
        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);
        xAxis.setDrawAxisLine(false);

        // Left axis
        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMinimum(0);
        yAxis.setDrawLabels(false);
        yAxis.setDrawGridLines(false);
        yAxis.setEnabled(false);

        // Right axis
        YAxis yAxis2 = barChart.getAxisRight();
        yAxis2.setDrawLabels(false);
        yAxis2.setDrawGridLines(false);
        yAxis2.setEnabled(false);
        yAxis2.setDrawAxisLine(false);

        // Set appearance params.
        Description description = new Description();
        description.setText("");
        barChart.setDescription(description);
        barChart.setFitBars(false);
        barChart.setHighlightPerTapEnabled(true);
        barChart.setDrawGridBackground(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setHighlightFullBarEnabled(false);
        barChart.setDragEnabled(false);
        barChart.setDrawBorders(false);
        barChart.setDrawMarkers(false);
        barChart.setTouchEnabled(false);
        barChart.getLegend().setEnabled(false);

        // Refresh.
        barChart.invalidate();
    }
}
