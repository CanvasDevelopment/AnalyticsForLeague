package com.teamunemployment.lolanalytics.Jungle;

import com.github.mikephil.charting.charts.BarChart;
import com.teamunemployment.lolanalytics.Jungle.Model.AdapterPojo;
import com.teamunemployment.lolanalytics.Base.BasePresenter;

import junit.framework.Assert;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * @author Josiah kendall
 */
public class JunglePresenterTests {

    @Test
    public void TestThatWeCanCreateBarChartFromJunglePojo() {
        AdapterPojo testPojo = new AdapterPojo();
        BasePresenter basePresenter = new BasePresenter(null);
        BarChart barChart = basePresenter.CreateJungleBarChart(testPojo, null);
        Assert.assertTrue(barChart != null);
    }
}
