package com.teamunemployment.lolanalytics.Jungle.PresentationLayer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.teamunemployment.lolanalytics.Jungle.Model.BarChartFactory;
import com.teamunemployment.lolanalytics.Jungle.Model.JungleAdapterPojo;
import com.teamunemployment.lolanalytics.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Josiah Kendall.
 * Not sure if this needs to be a specific chart class (e.g bar chart) It will probably work as a generic
 * chart view. Chart library seems to handle the polymorphism really well.
 */
public class BarChartCardView extends RecyclerView.ViewHolder implements ChartCardViewContract{

    private Context context;
    private RelativeLayout cardInner;

    BarChartFactory factory;

    @Bind(R.id.chart)
    BarChart barChart;

    public BarChartCardView(View itemView, Context context) {
        super(itemView);
        this.context = context;
        this.cardInner = (RelativeLayout) itemView;
        factory = new BarChartFactory();
        ButterKnife.bind(this, cardInner);
    }

    public void setBarChartData(JungleAdapterPojo jungleAdapterPojo) {

        // We have our bar chart. Now we need to add stuff to it.
        List<BarEntry> entries = new ArrayList<>();
        BarEntry me = new BarEntry(0f, new Float(jungleAdapterPojo.friendlyStats));
        BarEntry them = new BarEntry(1f, new Float(jungleAdapterPojo.enemyStats));


        entries.add(me);
        entries.add(them);
        BarDataSet dataSet = factory.createBarChartDataSet(entries, jungleAdapterPojo.title);

        if (jungleAdapterPojo.friendlyStats > jungleAdapterPojo.enemyStats) {
            dataSet.setColors(new int[]{R.color.green, R.color.grey}, context); // WOuld be real cool to not use context here if possible.
        } else {
            dataSet.setColors(new int[]{R.color.red, R.color.grey}, context); // WOuld be real cool to not use context here if possible.
        }
        BarData barData = new BarData(dataSet);
        barChart.setData(barData);
        Description description = new Description();
        description.setText("");
        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);
        xAxis.setDrawAxisLine(false);

        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMinimum(0);
        yAxis.setDrawLabels(false);
        yAxis.setDrawGridLines(false);
        yAxis.setEnabled(false);
        YAxis yAxis2 = barChart.getAxisRight();
        yAxis2.setDrawLabels(false);
        yAxis2.setDrawGridLines(false);
        yAxis2.setEnabled(false);
        yAxis2.setDrawAxisLine(false);


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
        barChart.invalidate();
    }

    @Override
    public void AddChart(Chart chart) {
        cardInner.addView(chart);
    }
}
