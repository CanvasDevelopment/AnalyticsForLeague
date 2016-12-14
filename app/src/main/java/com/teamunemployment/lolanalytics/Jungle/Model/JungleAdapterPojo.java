package com.teamunemployment.lolanalytics.Jungle.Model;

import com.teamunemployment.lolanalytics.Jungle.PresentationLayer.JungleAdapter;

/**
 * @author Josiah Kendall.
 *
 * Data fetching not yet implemented.
 */
public class JungleAdapterPojo {

    public double enemyStats;
    public double friendlyStats;

    public JungleAdapterPojo(Double ememyStats, Double friendlyStats) {
        this.enemyStats = ememyStats;
        this.friendlyStats = friendlyStats;
    }

    public JungleAdapterPojo(int enemyStats, int friendlyStats) {
        this.enemyStats = new Double(enemyStats);
        this.friendlyStats = new Double(friendlyStats);
    }
}
