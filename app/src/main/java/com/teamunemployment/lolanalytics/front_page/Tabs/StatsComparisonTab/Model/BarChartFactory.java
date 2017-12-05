package com.teamunemployment.lolanalytics.front_page.Tabs.StatsComparisonTab.Model;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.List;

/**
 * @author Josiah Kendall.
 *
 * This is a factory class used to generate the different classes for the bar charts. Factory class
 * used so it is easier to mock/test without having to pass around multiple little value objects
 */
public class BarChartFactory {
    public BarDataSet createBarChartDataSet(List<BarEntry> entries , String title) {
        return new BarDataSet(entries, title);
    }

    public BarData GenerateBarDataUsingDataSet(BarDataSet dataSet) {
        // Finally, add our value to the chart.
        BarData barData = new BarData(dataSet);
        return barData;
    }

    /**
     * Generate a bar entry using given x and y values.
     * @param x The x axis value
     * @param y The y axis value
     * @return the generated bar entry
     */
    public BarEntry GenerateBarEntry(float x, float y) {
        return new BarEntry(x, y);
    }


}
