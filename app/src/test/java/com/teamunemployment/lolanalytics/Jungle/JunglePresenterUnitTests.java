package com.teamunemployment.lolanalytics.Jungle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.teamunemployment.lolanalytics.Jungle.Model.BarChartFactory;
import com.teamunemployment.lolanalytics.Jungle.Model.AdapterPojo;
import com.teamunemployment.lolanalytics.Base.BasePresenter;
import com.teamunemployment.lolanalytics.Base.BaseModel;
import com.teamunemployment.lolanalytics.Base.ViewFragmentContract;

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
        AdapterPojo testPojo = new AdapterPojo(1, 2);
        BaseModel junglemodel = mock(BaseModel.class);
        BasePresenter basePresenter = new BasePresenter(junglemodel);
        ViewFragmentContract viewFragmentContract = Mockito.mock(ViewFragmentContract.class);
        basePresenter.setView(viewFragmentContract);
        // Ensure that we return an object when we get a bar chart from the view, otherwise assert will fail.
        BarChart barChart = Mockito.mock(BarChart.class);
        when(viewFragmentContract.createBarChart()).thenReturn(barChart);
        BarChartFactory barChartFactory = mock(BarChartFactory.class);

        BarDataSet barDataSet = mock(BarDataSet.class);
        when(barChartFactory.createBarChartDataSet(anyList(), anyString())).thenReturn(barDataSet);
    }
}
