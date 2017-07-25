package com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab.model;

import java.util.ArrayList;

/**
 * Created by Josiah Kendall
 */

public class ThreeGameStageStatistic {

    private String title;
    private GameStageStatisticModel earlyGame;
    private GameStageStatisticModel midGame;
    private GameStageStatisticModel lateGame;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setEarlyGame(GameStageStatisticModel earlyGame) {
        this.earlyGame = earlyGame;
    }

    public void setLateGame(GameStageStatisticModel lateGame) {
        this.lateGame = lateGame;
    }

    public void setMidGame(GameStageStatisticModel midGame) {
        this.midGame = midGame;
    }

    public GameStageStatisticModel getEarlyGame() {
        return earlyGame;
    }

    public GameStageStatisticModel getLateGame() {
        return lateGame;
    }

    public GameStageStatisticModel getMidGame() {
        return midGame;
    }
}
