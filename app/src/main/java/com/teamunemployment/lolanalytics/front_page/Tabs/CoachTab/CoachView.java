package com.teamunemployment.lolanalytics.front_page.Tabs.CoachTab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.teamunemployment.lolanalytics.App;
import com.teamunemployment.lolanalytics.front_page.Tabs.TabContract;
import com.teamunemployment.lolanalytics.R;

import java.util.ArrayList;

import javax.inject.Inject;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Josiah Kendall
 */

public class CoachView extends Fragment implements CoachContract.View{

    @BindView(R.id.win_rate_chart) LineChart lineChart;

    @Inject
    public CoachPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.coach_tab, container, false);

        // Trigger DI
        ((App) getActivity().getApplication()).getNetComponent().InjectView(this);

        // Bind butterknife to our view.
        ButterKnife.bind(this, rootView);

        presenter.setView(this);
        // Top is default role
        //presenter.setRole(role);
        presenter.startLoadInfo(-1);
        return rootView;
    }
    @Override
    public void setWinRateGraph(ArrayList<Entry> gameWinRate, ArrayList<Entry> laneWinRate) {
        LineDataSet gameWinRateDataSet = new LineDataSet(gameWinRate, "Game Win %");
        gameWinRateDataSet.setColor(getContext().getResources().getColor(R.color.green));
        gameWinRateDataSet.setDrawCircles(false);
        gameWinRateDataSet.setValueTextColor(Color.WHITE);
        LineDataSet laneWinRateDataSet = new LineDataSet(laneWinRate, "Lane win %");
        laneWinRateDataSet.setColor(getContext().getResources().getColor(R.color.colorAccent));
        laneWinRateDataSet.setDrawCircles(false);
        laneWinRateDataSet.setValueTextColor(Color.WHITE);
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(laneWinRateDataSet); // add the datasets
        dataSets.add(gameWinRateDataSet);
        styleLineChart(lineChart);
        // create a value object with the datasets
        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.animateX(300);
    }


    private void styleLineChart(LineChart lineChart) {
        lineChart.setAutoScaleMinMaxEnabled(false);

        Description chartDescription = new Description();
        chartDescription.setTextColor(Color.parseColor("#d7ccc8"));
        chartDescription.setText("Floating Five Game Average");
        lineChart.setDescription(chartDescription);
        YAxis yAxisLeft = lineChart.getAxisLeft();
        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisLeft.setAxisMinimum(20);
        yAxisLeft.setAxisMaximum(80);
        yAxisLeft.setDrawGridLines(false);
        yAxisLeft.setTextColor(Color.parseColor("#d7ccc8"));
        yAxisRight.setAxisMinimum(0);
        yAxisRight.setAxisMaximum(100);
        yAxisRight.setEnabled(false);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisLineColor(Color.parseColor("#d7ccc8"));
        xAxis.setTextColor(Color.parseColor("#d7ccc8"));
        xAxis.setDrawGridLines(false);
    }

    @Override
    public void setRecylcerViewAdapter(CoachHorizontalScrollerAdapter adapter) {

    }

    @Override
    public void setGoals(Object notReallySureWhatISGoingToGoHEre) {

    }

    @Override
    public void setIssues(Object notSureWhatHappensHere) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void setRole(int role) {

    }

    @Override
    public void setAdapter(TabContract.TabAdapter adapter) {

    }

    @Override
    public void setLoadingVisible(boolean visible) {

    }

//    private ArrayList<Entry> getMockData() {
//
//    }
}
