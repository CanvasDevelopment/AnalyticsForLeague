package com.teamunemployment.lolanalytics.front_page.Tabs.StatsComparisonTab;

import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.BarChartCardView;
import com.teamunemployment.lolanalytics.front_page.Tabs.StatsComparisonTab.Model.BarChartModel;

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
