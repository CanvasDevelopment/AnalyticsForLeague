package com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.Model;

import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.CardData;

/**
 * @author Josiah Kendall
 */

public class MatchHistoryCardData {
    private long champId;
    private String champName;
    private CardData kills;
    private CardData deaths;
    private CardData csFirstTen;
    private CardData csSecondTen;
    private CardData csTotal;

    // Default
    public MatchHistoryCardData() {}

    //
    public MatchHistoryCardData(long champId, String champName, CardData kills, CardData deaths, CardData csFirstTen, CardData csSecondTen, CardData csTotal) {
        this.champId = champId;
        this.champName = champName;
        this.kills = kills;
        this.csFirstTen = csFirstTen;
        this.csSecondTen = csSecondTen;
        this.csTotal = csTotal;
        this.deaths = deaths;
    }

    public void setChampId(long champId) {
        this.champId = champId;
    }

    public void setChampName(String champName) {
        this.champName = champName;
    }

    public void setCsFirstTen(CardData csFirstTen) {
        this.csFirstTen = csFirstTen;
    }

    public void setCsSecondTen(CardData csSecondTen) {
        this.csSecondTen = csSecondTen;
    }

    public void setCsTotal(CardData csTotal) {
        this.csTotal = csTotal;
    }

    public void setDeaths(CardData deaths) {
        this.deaths = deaths;
    }

    public void setKills(CardData kills) {
        this.kills = kills;
    }

    public CardData getCsFirstTen() {
        return csFirstTen;
    }

    public CardData getCsSecondTen() {
        return csSecondTen;
    }

    public CardData getCsTotal() {
        return csTotal;
    }

    public CardData getDeaths() {
        return deaths;
    }

    public CardData getKills() {
        return kills;
    }

    public long getChampId() {
        return champId;
    }

    public String getChampName() {
        return champName;
    }
}
