package com.teamunemployment.lolanalytics.Jungle.PresentationLayer;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.teamunemployment.lolanalytics.Jungle.Contracts.ModelPresenterContract;
import com.teamunemployment.lolanalytics.Jungle.JungleViewContract;
import com.teamunemployment.lolanalytics.Jungle.Model.BarChartFactory;
import com.teamunemployment.lolanalytics.Jungle.Model.JungleAdapterPojo;
import com.teamunemployment.lolanalytics.Jungle.Model.JungleModel;
import com.teamunemployment.lolanalytics.RecentHeadToHeadAverage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Josiah Kendall.
 */
public class JunglePresenter implements ModelPresenterContract {

    private JungleViewContract jungleView;
    private JungleModel jungleModel;
    private JungleAdapter jungleAdapter;

    @Inject
    public JunglePresenter(JungleModel jungleModel) {
        this.jungleModel = jungleModel;
    }

    // load the data and start the display.
    private void start() {
        // TODO shared preferences?
        String placeholder = "kloin";
        // Load data
        jungleModel.getCardPojos(placeholder, this);

       // jungleView.setJungleAdapter(jungleAdapter);
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


    public void showMessageToUser(String s) {
        jungleView.showMessage(s);
    }

    @Override
    public void addStatToList(JungleAdapterPojo jungleAdapterPojo) {
        //jungleAdapter.AddItem(jungleAdapterPojo);
        //jungleAdapter.notifyDataSetChanged();
    }

    @Override
    public void addDataToAdapter(ArrayList<JungleAdapterPojo> adapterPojos) {
        jungleAdapter = new JungleAdapter(adapterPojos, this);
        jungleView.setJungleAdapter(jungleAdapter);
    }
}
