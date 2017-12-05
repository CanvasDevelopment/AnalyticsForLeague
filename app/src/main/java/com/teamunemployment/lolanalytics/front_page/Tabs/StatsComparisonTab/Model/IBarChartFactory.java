package com.teamunemployment.lolanalytics.front_page.Tabs.StatsComparisonTab.Model;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.List;

/**
 * Created by jek40 on 1/12/2016.
 */
public interface IBarChartFactory {
    BarDataSet createBarChartDataSet(List<BarEntry> entries , String title);
}
