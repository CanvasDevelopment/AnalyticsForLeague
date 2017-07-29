package com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab.model;

import com.github.mikephil.charting.data.PieData;

/**
 * Created by Josiah Kendall
 */

public class StatisticsCardDataObject {

    private String title;
    private PieData earlyGameChartData;
    private PieData midGameChartData;
    private PieData lateGameChartData;
    private String performancePercentage;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setEarlyGameChartData(PieData earlyGameChartData) {
        this.earlyGameChartData = earlyGameChartData;
    }

    public void setLateGameChartData(PieData lateGameChartData) {
        this.lateGameChartData = lateGameChartData;
    }

    public void setMidGameChartData(PieData midGameChartData) {
        this.midGameChartData = midGameChartData;
    }

    public PieData getEarlyGameChartData() {
        return earlyGameChartData;
    }

    public PieData getLateGameChartData() {
        return lateGameChartData;
    }

    public PieData getMidGameChartData() {
        return midGameChartData;
    }

    public void setPerformancePercentage(String performancePercentage) {
        this.performancePercentage = performancePercentage;
    }

    public String getPerformancePercentage() {
        return performancePercentage;
    }
}
