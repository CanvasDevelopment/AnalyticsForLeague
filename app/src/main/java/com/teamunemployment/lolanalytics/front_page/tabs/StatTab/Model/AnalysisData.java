package com.teamunemployment.lolanalytics.front_page.tabs.StatTab.Model;

/**
 * Created by Josiah Kendall
 */

public class AnalysisData {

    private String title;
    private float enemyPercentTotal;
    private float heroPercentTotal;
    private double recentChange;

    public float getEnemyPercentTotal() { return enemyPercentTotal; }

    public float getHeroPercentTotal() {
        return heroPercentTotal;
    }

    public double getRecentChange() {
        return recentChange;
    }

    public String getTitle() {
        return title;
    }

    public void setEnemyPercentTotal(float enemyPercentTotal) {
        this.enemyPercentTotal = enemyPercentTotal;
    }

    public void setHeroPercentTotal(float heroPercentTotal) {
        this.heroPercentTotal = heroPercentTotal;
    }

    public void setRecentChange(double recentChange) {
        this.recentChange = recentChange;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
