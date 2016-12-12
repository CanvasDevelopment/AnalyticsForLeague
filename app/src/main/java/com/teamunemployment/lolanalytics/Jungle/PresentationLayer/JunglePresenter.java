package com.teamunemployment.lolanalytics.Jungle.PresentationLayer;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.teamunemployment.lolanalytics.Jungle.JungleViewContract;
import com.teamunemployment.lolanalytics.Jungle.Model.BarChartFactory;
import com.teamunemployment.lolanalytics.Jungle.Model.JungleAdapterPojo;
import com.teamunemployment.lolanalytics.Jungle.Model.JungleModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Josiah Kendall.
 */
public class JunglePresenter {

    private JungleViewContract jungleView;
    private JungleModel jungleModel;

    @Inject
    public JunglePresenter(JungleModel jungleModel) {
        this.jungleModel = jungleModel;
    }

    // load the data and start the display.
    private void start() {
        // Load data
        ArrayList<JungleAdapterPojo> pojos = jungleModel.getCardPojos();
        JungleAdapter jungleAdapter = new JungleAdapter(pojos, this);
        jungleView.setJungleAdapter(jungleAdapter);
    }

    /**
     * Set our current view, so that we can interact with it. This method also starts the loading, and
     * construction of the recycler view.
     * @param jungleView
     */
    public void setView(JungleViewContract jungleView) {
        this.jungleView = jungleView;
        start();
    }

    /**
     * Create a barchart with our jungle on it.
     * @param jungleAdapterPojo the pojo with the jungle data.
     * @return The barchart that we have created.
     */
    public BarChart CreateJungleBarChart(JungleAdapterPojo jungleAdapterPojo, BarChartFactory factory) {
        if (jungleView == null) {
            throw new NullPointerException("Error - set view before using any methods in thes presenter");
        }

        BarChart barChart = jungleView.createBarChart();

        // We have our bar chart. Now we need to add stuff to it.
        List<BarEntry> entries = new ArrayList<>();
        BarEntry me = new BarEntry(1, 1);
        BarEntry them = new BarEntry(2, 1);
        entries.add(me);
        entries.add(them);
        BarDataSet dataSet = factory.createBarChartDataSet(entries, "My Title");
//        BarDataSet dataSet = new BarDataSet(entries, "My Title");
        BarData barData = new BarData(dataSet);
        barChart.setData(barData);
        return barChart;
    }







}
