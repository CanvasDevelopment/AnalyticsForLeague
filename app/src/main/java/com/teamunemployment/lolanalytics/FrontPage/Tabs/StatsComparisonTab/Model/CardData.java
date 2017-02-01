package com.teamunemployment.lolanalytics.StatsComparisonTab.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Josiah Kendall.
 *
 * Data fetching not yet implemented.
 */
public class CardData extends RealmObject {

    @PrimaryKey
    private int id;
    private String title;
    private double enemyStats;
    private double friendlyStats;
    private int role;
    private long summonerId;

    // Default constructor required for realm
    public CardData() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEnemyStats(double enemyStats) {
        this.enemyStats = enemyStats;
    }

    public void setFriendlyStats(double friendlyStats) {
        this.friendlyStats = friendlyStats;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getEnemyStats() {
        return enemyStats;
    }

    public double getFriendlyStats() {
        return friendlyStats;
    }

    public int getRole() {
        return role;
    }

    public String getTitle() {
        return title;
    }

    public long getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(long summonerId) {
        this.summonerId = summonerId;
    }

    public CardData(Double ememyStats, Double friendlyStats) {
        this.enemyStats = ememyStats;
        this.friendlyStats = friendlyStats;
    }

    public CardData(int enemyStats, int friendlyStats) {
        this.enemyStats = new Double(enemyStats);
        this.friendlyStats = new Double(friendlyStats);
    }

    public CardData(int enemyStats, int friendlyStats, String title) {
        this.enemyStats = enemyStats;
        this.friendlyStats = friendlyStats;
        this.title = title;
    }

    public CardData(int enemyStats, int friendlyStats, String title, int id, int role, int summonerId) {
        this.enemyStats = enemyStats;
        this.friendlyStats = friendlyStats;
        this.title = title;
        this.id = id;
        this.role = role;
        this.summonerId = summonerId;
    }
}
