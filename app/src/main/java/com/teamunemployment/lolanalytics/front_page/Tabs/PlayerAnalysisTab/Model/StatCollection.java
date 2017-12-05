package com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab.Model;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

/**
 * @author Josiah Kendall
 *
 * wrapper calls for our stat object and the associated
 */

public class StatCollection {

    private ArrayList<Entry> collection;
    private ArrayList<Entry> enemyCollection;
    private StatSummary statSummary;

    public void setCollection(ArrayList<Entry> collection, ArrayList<Entry> enemyCollection) {
        this.collection = collection;
        this.enemyCollection = enemyCollection;
    }

    public ArrayList<Entry> getCollection() {
        return collection;
    }

    public StatSummary getStatSummary() {
        return statSummary;
    }

    public void setStatSummary(StatSummary statSummary) {
        this.statSummary = statSummary;
    }

    public ArrayList<Entry> getEnemyCollection() {
        return enemyCollection;
    }
}
