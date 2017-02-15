package com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab;

import com.github.mikephil.charting.data.BarDataSet;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.CardData;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.BarChartModel;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.BarChartFactory;

import org.junit.Test;

import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Josiah Kendall
 */

public class BarChartModeTests {

    @Test
    public void TestWeCreateTwoBarEntriesForABarChartGivenAPojoWithCertainValues() {
        BarChartFactory barChartFactory = mock(BarChartFactory.class);
        BarDataSet dataSet = mock(BarDataSet.class);
        when(barChartFactory.createBarChartDataSet(anyList(), anyString())).thenReturn(dataSet);
        BarChartModel barChartModel = new BarChartModel(barChartFactory);

        CardData cardData = new CardData(23, 34, "This is a test");
        barChartModel.FetchBarDataSet(cardData);
        verify(barChartFactory, times(2)).GenerateBarEntry(anyFloat(), anyFloat());
    }

    @Test
    public void TestThatWeSetNullDescription() {

    }
}
