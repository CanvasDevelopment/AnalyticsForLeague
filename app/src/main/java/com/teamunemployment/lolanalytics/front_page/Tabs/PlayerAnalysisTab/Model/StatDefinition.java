package com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab.Model;

/**
 * @author Josiah Kendall
 */

public class StatDefinition {

    private String statName;
    private int statId;

    public void setStatId(int statId) {
        this.statId = statId;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    public int getStatId() {
        return statId;
    }

    public String getStatName() {
        return statName;
    }
}
