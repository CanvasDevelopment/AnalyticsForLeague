package com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab;

import com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.Cards.MatchHistoryCardViewContract;
import com.teamunemployment.lolanalytics.PresenterContract;
import com.teamunemployment.lolanalytics.Data.model.MatchIdWrapper;
import com.teamunemployment.lolanalytics.Data.model.MatchSummary;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.Model.MatchHistoryCardData;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.TabContract;

import java.util.ArrayList;

/**
 * @author Josiah Kendall
 */
public class MatchHistoryTabContract {

    interface Presenter extends PresenterContract {
        void LoadDataForRole(int role, long summonerId);
        void LoadMatchSummary(long matchId);
        void setView(TabContract.View view);
        void onMatchSummaryLoadedSuccessfully(MatchSummary matchSummary);
        void onError(Throwable e);
        void onMatchListLoadedSuccessfully(ArrayList<MatchIdWrapper> matchHistoryData);
        void setRole(int role);
        void LoadCardData(long id, MatchHistoryCardViewContract cardViewContract);
        void SetLoadedCardData(MatchHistoryCardData matchHistoryCardData, MatchHistoryCardViewContract cardViewContract);
    }
}
