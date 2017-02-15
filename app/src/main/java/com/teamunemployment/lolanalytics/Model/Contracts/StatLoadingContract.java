package com.teamunemployment.lolanalytics.Model.Contracts;

import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatCollection;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatSummary;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatPoint;

/**
 * @author Josiah Kendall
 */
public interface StatLoadingContract {

    interface StatDataContract {
        void onStatDataLoaded(StatSummary statSummary);
    }

    interface StatPointContract {
        void onStatDetailsLoaded(StatPoint statPoint);
    }

    interface StatCollectionContract {
        void onStatCollectionLoaded(StatCollection collection);
        void onStatCollectionFailed(String message);
    }
}
