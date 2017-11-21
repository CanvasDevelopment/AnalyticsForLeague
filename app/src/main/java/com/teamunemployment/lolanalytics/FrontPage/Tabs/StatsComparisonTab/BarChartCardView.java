package com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.BarChartModel;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.CardData;
import com.teamunemployment.lolanalytics.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Josiah Kendall.
 * Not sure if this needs to be a specific chart class (e.g bar chart) It will probably work as a generic
 * chart view. Chart library seems to handle the polymorphism really well.
 */
public class BarChartCardView extends RecyclerView.ViewHolder implements ChartCardViewContract {

    private Context context;
    private CardView cardInner;

    private BarChartModel barChartModel;

    @BindView(R.id.chart) BarChart barChart;

    @BindView(R.id.graph_title) TextView title;

    public BarChartCardView(View itemView, Context context, BarChartModel barChartModel) {
        super(itemView);
        this.context = context;
        this.cardInner = (CardView) itemView;
        this.barChartModel = barChartModel;
        ButterKnife.bind(this, cardInner);
    }

    /**
     * Set the title on the card that comes with the dataset from the server.
     * @param titleString
     */
    public void setTitle(String titleString) {
        title.setText(titleString);
    }

    /**
     * Set the data to the bar chart.
     * @param cardData The pojo with the data on it.
     */
    public void setBarChartData(CardData cardData) {

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

    @Override
    public void AddChart(Chart chart) {
        cardInner.addView(chart);
    }
}
