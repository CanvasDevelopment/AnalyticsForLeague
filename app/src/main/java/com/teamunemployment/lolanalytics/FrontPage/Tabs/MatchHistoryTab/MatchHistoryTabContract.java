package com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab;

import com.teamunemployment.lolanalytics.BasePresenterContract;
import com.teamunemployment.lolanalytics.Data.model.MatchIdWrapper;
import com.teamunemployment.lolanalytics.Data.model.MatchSummary;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.Model.MatchHistoryCardData;
import com.teamunemployment.lolanalytics.ViewContract;

import java.util.ArrayList;

/**
 * @author Josiah Kendall
 */
public class MatchHistoryTabContract {

    interface BasePresenter extends BasePresenterContract {
        void LoadDataForRole(int role, long summonerId);
        void LoadMatchSummary(long matchId);
        void setView(MatchHistoryTabContract.View view);
        void onMatchSummaryLoadedSuccessfully(MatchSummary matchSummary);
        void onError(Throwable e);
        void onMatchListLoadedSuccessfully(ArrayList<MatchIdWrapper> matchHistoryData);
        void setRole(int role);
        void LoadCardData(long id, MatchHistoryCardViewContract cardViewContract);
        void SetLoadedCardData(MatchHistoryCardData matchHistoryCardData, MatchHistoryCardViewContract cardViewContract);
    }

    interface View extends ViewContract {
        void setRole(int role);
        void setAdapter(MatchHistoryAdapter adapter);
        void updateActivityRole(int role);

    }

}
