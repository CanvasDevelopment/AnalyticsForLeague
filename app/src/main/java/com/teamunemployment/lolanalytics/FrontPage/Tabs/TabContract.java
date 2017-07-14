package com.teamunemployment.lolanalytics.FrontPage.Tabs;

import com.teamunemployment.lolanalytics.Data.model.MatchIdWrapper;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.MatchHistoryAdapter;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatDefinition;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.CardData;
import com.teamunemployment.lolanalytics.ViewContract;

import java.util.ArrayList;

/**
 * @author Josiah Kendall
 */

public interface TabContract {

    interface View extends ViewContract {
        void setRole(int role);
        void setAdapter(TabAdapter adapter);
    }

    /**
     * We need a setData() method for each of the tabs, because each of the tabs have a different data
     * package delivered to them from the server. // TODO rewrite data delivery to use a wrapper and one setData() method on the contract.
     */
    interface TabAdapter {
        // MatchHistoryAdapter
        void setMatchHistoryAdapterData(ArrayList<MatchIdWrapper> matchIds);
        void setPlayerAnalysisAdapterData(ArrayList<StatDefinition> statDefinitions);
        void setStatComparisonAdapterData(ArrayList<CardData> cardDatas);
    }
}
