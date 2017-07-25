package com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab.model;

/**
 * Created by Josiah Kendall
 */

public class GameStageStatisticModel {

    private int gameStage;
    private float enemyStat;
    private float heroStat;

    public void setEnemyStat(float enemyStat) {
        this.enemyStat = enemyStat;
    }

    public void setGameStage(int gameStage) {
        this.gameStage = gameStage;
    }

    public void setHeroStat(float heroStat) {
        this.heroStat = heroStat;
    }

    public float getEnemyStat() {
        return enemyStat;
    }

    public float getHeroStat() {
        return heroStat;
    }

    public int getGameStage() {
        return gameStage;
    }
}
