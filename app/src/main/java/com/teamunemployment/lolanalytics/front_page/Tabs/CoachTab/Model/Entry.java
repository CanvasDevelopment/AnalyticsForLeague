package com.teamunemployment.lolanalytics.front_page.Tabs.CoachTab.Model;

import io.realm.RealmObject;

/**
 * A version of the entry for the graph. We need this to save to the database.
 */

public class Entry extends RealmObject {

    private float x;
    private float y;
    private long summonerId;
    private int statId;

    public Entry() {
        // default constructor
    }

    public Entry(float x, float y, long summonerId, int statId) {
        this.x = x;
        this.y = y;
        this.summonerId = summonerId;
        this.statId = statId;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setStatId(int statId) {
        this.statId = statId;
    }

    public int getStatId() {
        return statId;
    }

    public long getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(long summonerId) {
        this.summonerId = summonerId;
    }

    public com.github.mikephil.charting.data.Entry getChartEntry() {
        return new com.github.mikephil.charting.data.Entry(x, y);
    }
}
