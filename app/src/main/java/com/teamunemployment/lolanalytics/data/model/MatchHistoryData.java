package com.teamunemployment.lolanalytics.data.model;

import java.util.ArrayList;

/**
 * @author Josiah Kendall.
 */
public class MatchHistoryData {

    public String summonerId;
    public int season;
    public int role;
    public ArrayList<MatchIdWrapper> items;

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public void setItems(ArrayList<MatchIdWrapper> items) {
        this.items = items;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public ArrayList<MatchIdWrapper> getItems() {
        return items;
    }

    public int getRole() {
        return role;
    }

    public int getSeason() {
        return season;
    }
}
