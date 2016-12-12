package com.teamunemployment.lolanalytics.Jungle;

import com.github.mikephil.charting.charts.BarChart;
import com.teamunemployment.lolanalytics.Jungle.Model.JungleAdapterPojo;
import com.teamunemployment.lolanalytics.Jungle.Model.JungleModel;
import com.teamunemployment.lolanalytics.Jungle.PresentationLayer.JunglePresenter;

import junit.framework.Assert;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * @author Josiah kendall
 */
public class JunglePresenterTests {

    @Test
    public void TestThatWeCanCreateBarChartFromJunglePojo() {
        JungleAdapterPojo testPojo = new JungleAdapterPojo();

        JunglePresenter junglePresenter = new JunglePresenter(null);
        BarChart barChart = junglePresenter.CreateJungleBarChart(testPojo, null);
        Assert.assertTrue(barChart != null);
    }
}
