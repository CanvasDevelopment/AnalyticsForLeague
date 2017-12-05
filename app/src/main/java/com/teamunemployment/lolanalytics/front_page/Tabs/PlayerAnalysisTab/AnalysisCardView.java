package com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab.Model.StatDefinition;
import com.teamunemployment.lolanalytics.R;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Josiah Kendall
 */

public class AnalysisCardView extends RecyclerView.ViewHolder implements PlayerAnalysisCardViewContract{

    private View cardBase;
    private Context context;
    private PlayerAnalysisPresenter playerAnalysisBasePresenter;
    private boolean collapsed = true;
    private StatDefinition statDefinition;

    @BindView(R.id.total_change) TextView improvementValue;
    @BindView(R.id.graph_wrapper) RelativeLayout graphWrapper;
    @BindView(R.id.line_chart) LineChart lineChart;
    @BindView(R.id.item_title) TextView title;
    @BindView(R.id.list_item_subtitle) TextView subTitle;
    @BindView(R.id.trend) ImageView trendView;

    public AnalysisCardView(View itemView, Context context, PlayerAnalysisPresenter playerAnalysisBasePresenter) {
        super(itemView);
        this.cardBase = itemView;
        this.context = context;
        ButterKnife.bind(this, itemView);
        this.playerAnalysisBasePresenter = playerAnalysisBasePresenter;
    }

    private void loadData(StatDefinition statDefinition) {
        playerAnalysisBasePresenter.LoadStatDetails(statDefinition.getStatId(), -1, this);
    }

    public void LoadFaceData(int id) {
        // TODO
        playerAnalysisBasePresenter.LoadStatDetails(statDefinition.getStatId(), -1, this);
    }

    /**
     * Set the stat definition that we want to use for the loading.
     * @param statDefinition
     */
    public void SetStatDefinition(StatDefinition statDefinition) {
        this.statDefinition = statDefinition;
    }

    @Override
    public void setStatCollection(ArrayList<Entry> heroStats, ArrayList<Entry> enemyStats) {
        LineDataSet lineDataSet = new LineDataSet(heroStats, "Me");
        lineDataSet.setColor(context.getResources().getColor(R.color.green));
        lineDataSet.setDrawCircles(false);
        lineDataSet.setValueTextColor(Color.WHITE);
        LineDataSet enemyDataSet = new LineDataSet(enemyStats, "Enemy Lane");
        enemyDataSet.setColor(context.getResources().getColor(R.color.colorAccent));
        enemyDataSet.setDrawCircles(false);
        enemyDataSet.setValueTextColor(Color.WHITE);
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(lineDataSet); // add the datasets
        dataSets.add(enemyDataSet);
        styleLineChart(lineChart);

        // create a value object with the datasets
        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.animateX(300);
    }

    private void styleLineChart(LineChart lineChart) {
        lineChart.setAutoScaleMinMaxEnabled(true);
        Description chartDescription = new Description();
        chartDescription.setTextColor(Color.WHITE);
        chartDescription.setText("Floating Five Game Average");
        lineChart.setDescription(chartDescription);
        YAxis yAxisLeft = lineChart.getAxisLeft();
        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisLeft.setAxisMinimum(0);
        yAxisLeft.setAxisMaximum(100);
        yAxisLeft.setTextColor(Color.WHITE);
        yAxisRight.setAxisMinimum(0);
        yAxisRight.setAxisMaximum(100);
        yAxisRight.setEnabled(false);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisLineColor(Color.WHITE);
        xAxis.setTextColor(Color.WHITE);
    }

    @Override
    public void setStatName(String statName) {
        title.setText(statName);
    }

    //TODO
    @Override
    public void setStatIcon(String iconUrl) {
        // Just randomly set for now.
        Random random = new Random();
        int randomInt =random.nextInt(3);
        if (randomInt == 2) {
            // Make sure we're running on Honeycomb or higher to use ActionBar APIs
            if (Build.VERSION.SDK_INT >= 21) {
                trendView.setImageDrawable(context.getDrawable(R.mipmap.ic_trending_down_white_24dp));
            } else {
                trendView.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_trending_down_white_24dp));
            }
            trendView.setColorFilter(ContextCompat.getColor(context,R.color.red));

        } else {
            // Make sure we're running on Honeycomb or higher to use ActionBar APIs
            if (Build.VERSION.SDK_INT >= 21) {
                trendView.setImageDrawable(context.getDrawable(R.mipmap.ic_trending_up_white_24dp));
            } else {
                trendView.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_trending_up_white_24dp));
            }
            trendView.setColorFilter(ContextCompat.getColor(context,R.color.green));

        }
    }

    @Override
    public void setStatSubTitle(String subName) {
        subTitle.setText(subName);
    }

    @Override
    public void setImprovementValue(double improvementValue) {
        this.improvementValue.setText(String.valueOf(improvementValue));
    }

    @OnClick(R.id.list_item_wrapper) void expandCollapseClickHandler() {

        if (collapsed) {
            collapsed = false;
            expand();
        } else {
            collapsed = true;
            collapse();
        }
    }

    // Expand the card item
    private void expand() {
        graphWrapper.setVisibility(View.VISIBLE);
        // load Data
        this.loadData(this.statDefinition);
    }

    private void collapse() {
        graphWrapper.setVisibility(View.GONE);
    }
}
