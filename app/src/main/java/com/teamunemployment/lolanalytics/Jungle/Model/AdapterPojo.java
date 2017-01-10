package com.teamunemployment.lolanalytics.Jungle.Model;

/**
 * @author Josiah Kendall.
 *
 * Data fetching not yet implemented.
 */
public class AdapterPojo {

    public String title;
    public double enemyStats;
    public double friendlyStats;

    public AdapterPojo(Double ememyStats, Double friendlyStats) {
        this.enemyStats = ememyStats;
        this.friendlyStats = friendlyStats;
    }

    public AdapterPojo(int enemyStats, int friendlyStats) {
        this.enemyStats = new Double(enemyStats);
        this.friendlyStats = new Double(friendlyStats);
    }

    public AdapterPojo(int enemyStats, int friendlyStats, String title) {
        this.enemyStats = enemyStats;
        this.friendlyStats = friendlyStats;
        this.title = title;
    }
}
