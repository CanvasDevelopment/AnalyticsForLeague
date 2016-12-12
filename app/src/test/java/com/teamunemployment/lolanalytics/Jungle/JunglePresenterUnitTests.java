package com.teamunemployment.lolanalytics.Jungle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.teamunemployment.lolanalytics.Jungle.Model.BarChartFactory;
import com.teamunemployment.lolanalytics.Jungle.Model.JungleAdapterPojo;
import com.teamunemployment.lolanalytics.Jungle.Model.JungleModel;
import com.teamunemployment.lolanalytics.Jungle.PresentationLayer.JunglePresenter;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Josiah Kendall
 */
public class JunglePresenterUnitTests {

    @Test
    public void TestThatWeCanCreateBarChartFromJunglePojo() {
        JungleAdapterPojo testPojo = new JungleAdapterPojo();
        JungleModel junglemodel = mock(JungleModel.class);
        JunglePresenter junglePresenter = new JunglePresenter(junglemodel);
        JungleViewContract jungleViewContract = Mockito.mock(JungleViewContract.class);
        junglePresenter.setView(jungleViewContract);
        // Ensure that we return an object when we get a bar chart from the view, otherwise assert will fail.
        BarChart barChart = Mockito.mock(BarChart.class);
        when(jungleViewContract.createBarChart()).thenReturn(barChart);
        BarChartFactory barChartFactory = mock(BarChartFactory.class);

        BarDataSet barDataSet = mock(BarDataSet.class);
        when(barChartFactory.createBarChartDataSet(anyList(), anyString())).thenReturn(barDataSet);
        // Check how the method works
        BarChart returnedBarChart = junglePresenter.CreateJungleBarChart(testPojo, barChartFactory);
        assertTrue(returnedBarChart != null);
    }
}
