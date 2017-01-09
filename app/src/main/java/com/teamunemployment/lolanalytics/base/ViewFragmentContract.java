package com.teamunemployment.lolanalytics.base;

import com.github.mikephil.charting.charts.BarChart;

/**
 * @author Josiah Kendall.
 */
public interface ViewFragmentContract {

    // Needs to be on the view as it runs on the main thread. do i need this?
    BarChart createBarChart();
    void setJungleAdapter(BaseRecyclerAdapter baseRecyclerAdapter);
    void showMessage(String s);
}
