package com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

/**
 * @author Josiah Kendall
 */

public interface PlayerAnalysisCardViewContract {
    void setStatCollection(ArrayList<Entry> statPoints, ArrayList<Entry> enemyStats);
    void setStatName(String statName);
    void setStatIcon(String iconUrl);
    void setStatSubTitle(String subName);
    void setImprovementValue(double improvementValue);
}
