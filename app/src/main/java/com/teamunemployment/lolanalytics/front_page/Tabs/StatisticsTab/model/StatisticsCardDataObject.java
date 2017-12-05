package com.teamunemployment.lolanalytics.front_page.Tabs.StatisticsTab.model;

import com.github.mikephil.charting.data.PieData;

/**
 * Created by Josiah Kendall
 */

public class StatisticsCardDataObject {

    private String title;
    private PieData earlyGameChartData;
    private PieData midGameChartData;
    private PieData lateGameChartData;
    private String earlyGamePerformancePercentage;
    private String midGamePerformancePercentage;
    private String lateGamePerformancePercentage;

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

    public void setEarlyGamePerformancePercentage(String earlyGamePerformancePercentage) {
        this.earlyGamePerformancePercentage = earlyGamePerformancePercentage;
    }

    public String getEarlyGamePerformancePercentage() {
        return earlyGamePerformancePercentage;
    }

    public String getLateGamePerformancePercentage() {
        return lateGamePerformancePercentage;
    }

    public void setLateGamePerformancePercentage(String lateGamePerformancePercentage) {
        this.lateGamePerformancePercentage = lateGamePerformancePercentage;
    }

    public String getMidGamePerformancePercentage() {
        return midGamePerformancePercentage;
    }

    public void setMidGamePerformancePercentage(String midGamePerformancePercentage) {
        this.midGamePerformancePercentage = midGamePerformancePercentage;
    }
}
