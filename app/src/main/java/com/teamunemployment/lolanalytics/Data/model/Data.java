package com.teamunemployment.lolanalytics.Data.model;

import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.CardData;

import java.util.List;

/**
 * @author Josiah Kendall
 * This is the data wrapper for our objects that get returned from the server. We need this for retorfit
 * because Google App Engine has to return an object, which gets put inside a "data:" variable.
 */
public class Data {

    public int role;
    public List<CardData> items;

    public void setRole(int role) {
        this.role = role;
    }

    public void setItems(List<CardData> items) {
        this.items = items;

    }

    public int getRole() {
        return role;
    }

    public List<CardData> getItems() {
        return items;
    }
}
