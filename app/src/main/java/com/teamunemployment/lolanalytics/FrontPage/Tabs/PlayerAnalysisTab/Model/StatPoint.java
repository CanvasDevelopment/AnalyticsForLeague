package com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Josiah Kendall
 *
 * This class represents a point on the line graph on the player analysis graphs. One of more (usually a lot)
 * of StatPoint instances make up the data in the {@link StatSummary} object.
 */
public class StatPoint extends RealmObject {

    @PrimaryKey
    private int id;
    private long statId;
    private double xValue;
    private double yValue; // not sure if this is needed. We could probably just sort by id.

    public void setId(int id) {
        this.id = id;
    }

    public void setStatId(long statId) {
        this.statId = statId;
    }

    public void setxValue(double xValue) {
        this.xValue = xValue;
    }

    public void setyValue(double yValue) {
        this.yValue = yValue;
    }

    public double getxValue() {
        return xValue;
    }

    public double getyValue() {
        return yValue;
    }

    public int getId() {
        return id;
    }

    public long getStatId() {
        return statId;
    }
}
