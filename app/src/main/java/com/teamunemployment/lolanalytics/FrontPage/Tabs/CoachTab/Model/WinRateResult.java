package com.teamunemployment.lolanalytics.FrontPage.Tabs.CoachTab.Model;

import java.util.ArrayList;

/**
 * @author Josiah Kendall
 */
public class WinRateResult {

    private ArrayList<com.github.mikephil.charting.data.Entry> laneWinRate;
    private ArrayList<com.github.mikephil.charting.data.Entry> gameWinRate;

    public WinRateResult(ArrayList<com.github.mikephil.charting.data.Entry> laneWinRate, ArrayList<com.github.mikephil.charting.data.Entry> gameWinRate) {
        this.laneWinRate = laneWinRate;
        this.gameWinRate = gameWinRate;
    }

    public void setGameWinRate(ArrayList<com.github.mikephil.charting.data.Entry> gameWinRate) {
        this.gameWinRate = gameWinRate;
    }

    public void setLaneWinRate(ArrayList<com.github.mikephil.charting.data.Entry> laneWinRate) {
        this.laneWinRate = laneWinRate;
    }

    public ArrayList<com.github.mikephil.charting.data.Entry> getGameWinRate() {
        return gameWinRate;
    }

    public ArrayList<com.github.mikephil.charting.data.Entry> getLaneWinRate() {
        return laneWinRate;
    }
}
