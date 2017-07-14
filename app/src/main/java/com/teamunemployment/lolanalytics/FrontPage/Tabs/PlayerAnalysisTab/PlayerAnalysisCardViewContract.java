package com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab;

import com.github.mikephil.charting.data.Entry;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatCollection;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatPoint;

import java.util.ArrayList;

import io.realm.RealmResults;

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
