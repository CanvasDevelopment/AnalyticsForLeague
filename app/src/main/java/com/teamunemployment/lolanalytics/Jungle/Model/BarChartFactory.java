package com.teamunemployment.lolanalytics.Jungle.Model;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.List;

/**
 * @author Josiah Kendall.
 *
 */
public class BarChartFactory {
    public BarDataSet createBarChartDataSet(List<BarEntry> entries , String title) {
        return new BarDataSet(entries, title);
    }
}
