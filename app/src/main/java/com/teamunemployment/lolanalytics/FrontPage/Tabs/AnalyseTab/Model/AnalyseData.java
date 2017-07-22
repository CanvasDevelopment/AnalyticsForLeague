package com.teamunemployment.lolanalytics.FrontPage.Tabs.AnalyseTab.Model;

/**
 * Created by Josiah Kendall
 */

public class AnalyseData {

    private String title;
    private double enemyPercentTotal;
    private double heroPercentTotal;
    private double recentChange;

    public double getEnemyPercentTotal() {
        return enemyPercentTotal;
    }

    public double getHeroPercentTotal() {
        return heroPercentTotal;
    }

    public double getRecentChange() {
        return recentChange;
    }

    public String getTitle() {
        return title;
    }

    public void setEnemyPercentTotal(double enemyPercentTotal) {
        this.enemyPercentTotal = enemyPercentTotal;
    }

    public void setHeroPercentTotal(double heroPercentTotal) {
        this.heroPercentTotal = heroPercentTotal;
    }

    public void setRecentChange(double recentChange) {
        this.recentChange = recentChange;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
