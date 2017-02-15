package com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab;

import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.BarChartModel;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * @author Josiah Kendall
 */

public class AdapterTests {


    @Before

    @Test
    public void TestThatWeCanSetDataToTheBarChart() {

    }

    @Test(expected = IllegalStateException.class)
    public void TestThatWeGetIllegalStateExceptionIfWeBindBeforeSettingData() {
        TabRecyclerAdapter tabRecyclerAdapter = new TabRecyclerAdapter(mock(BarChartModel.class));
        tabRecyclerAdapter.onBindViewHolder(mock(BarChartCardView.class), 1);
    }

}
