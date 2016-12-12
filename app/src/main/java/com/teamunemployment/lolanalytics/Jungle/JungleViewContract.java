package com.teamunemployment.lolanalytics.Jungle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.teamunemployment.lolanalytics.Jungle.PresentationLayer.JungleAdapter;

/**
 * @author Josiah Kendall.
 */
public interface JungleViewContract {

    // Needs to be on the view as it runs on the main thread. do i need this?
    BarChart createBarChart();
    void setJungleAdapter(JungleAdapter jungleAdapter);
}
