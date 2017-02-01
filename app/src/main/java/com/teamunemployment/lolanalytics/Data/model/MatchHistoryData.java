package com.teamunemployment.lolanalytics.Data.model;

import java.util.ArrayList;

/**
 * @author Josiah Kendall.
 */
public class MatchHistoryData {

    public long SummonerId;
    public int season;
    public int role;
    public ArrayList<MatchSummary> items;

    public void setSummonerId(long summonerId) {
        SummonerId = summonerId;
    }

    public void setItems(ArrayList<MatchSummary> items) {
        this.items = items;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public long getSummonerId() {
        return SummonerId;
    }

    public ArrayList<MatchSummary> getItems() {
        return items;
    }

    public int getRole() {
        return role;
    }

    public int getSeason() {
        return season;
    }
}
