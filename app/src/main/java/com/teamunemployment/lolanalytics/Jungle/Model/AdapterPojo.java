package com.teamunemployment.lolanalytics.Jungle.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Josiah Kendall.
 *
 * Data fetching not yet implemented.
 */
public class AdapterPojo extends RealmObject {

    @PrimaryKey
    private int id;
    private String title;
    private double enemyStats;
    private double friendlyStats;
    private String role;

    public AdapterPojo() {

    }

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

    public void setRole(String role) {
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

    public String getRole() {
        return role;
    }

    public String getTitle() {
        return title;
    }

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

    public AdapterPojo(int enemyStats, int friendlyStats, String title, int id, String role) {
        this.enemyStats = enemyStats;
        this.friendlyStats = friendlyStats;
        this.title = title;
        this.id = id;
        this.role = role;
    }
}
