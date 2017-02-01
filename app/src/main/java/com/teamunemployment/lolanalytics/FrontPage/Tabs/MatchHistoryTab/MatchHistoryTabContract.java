package com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab;

import com.teamunemployment.lolanalytics.Data.model.MatchHistoryData;
import com.teamunemployment.lolanalytics.Data.model.MatchSummary;
import com.teamunemployment.lolanalytics.PresenterContract;
import com.teamunemployment.lolanalytics.ViewContract;

/**
 * @author Josiah Kendall
 */
public class MatchHistoryTabContract {

    interface Presenter extends PresenterContract {
        void LoadDataForRole(int role, long summonerId);
        void LoadMatchSummary(long matchId);
        void setView(MatchHistoryTabContract.View view);
        void onMatchSummaryLoadedSuccessfully(MatchSummary matchSummary);
        void onError(Throwable e);
        void onMatchListLoadedSuccessfully(MatchHistoryData matchHistoryData);
        void setRole(int role);
    }

    interface View extends ViewContract {

        void setAdapter(MatchHistoryAdapter adapter);
        void updateActivityRole(int role);

    }

}
