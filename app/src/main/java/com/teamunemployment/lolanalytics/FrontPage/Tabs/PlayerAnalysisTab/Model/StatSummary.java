package com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Josiah Kendall.
 *
 *
 */
public class StatSummary extends RealmObject {

    @PrimaryKey
    private long id;
    private boolean hasGoal;
    private double goalvalue;
    private String statName;
    private double improvementValue;

    public void setId(long id) {
        this.id = id;
    }

    public void setImprovementValue(double improvementValue) {
        this.improvementValue = improvementValue;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    public long getId() {
        return id;
    }

    public double getImprovementValue() {
        return improvementValue;
    }

    public String getStatName() {
        return statName;
    }

    public void setGoalvalue(double goalvalue) {
        this.goalvalue = goalvalue;
    }

    public void setHasGoal(boolean hasGoal) {
        this.hasGoal = hasGoal;
    }

    public double getGoalvalue() {
        return goalvalue;
    }

    public boolean getHasGoal() {
        return hasGoal;
    }
}
