package com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab;

import com.teamunemployment.lolanalytics.Data.Statics;
import com.teamunemployment.lolanalytics.Data.model.Data;
import com.teamunemployment.lolanalytics.Data.model.MatchHistoryData;
import com.teamunemployment.lolanalytics.Data.model.MatchSummary;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Josiah Kendall.
 */
public class MatchHistoryPresenter implements MatchHistoryTabContract.Presenter {

    private MatchHistoryModel matchHistoryModel;
    private MatchHistoryTabContract.View view;

    @Inject
    public MatchHistoryPresenter(MatchHistoryModel matchHistoryModel) {
        this.matchHistoryModel = matchHistoryModel;
    }

    @Override
    public void start() {
        LoadDataForRole(Statics.TOP, -1);
    }

    @Override
    public void handleError(Throwable e) {

    }

    @Override
    public void LoadDataForRole(int role, long summonerId) {

        if (view == null) {
            throw new IllegalStateException("Please set a view before calling start on this presenter");
        }

        matchHistoryModel.LoadCachedMatchHistoryData(role, -1, this);
        matchHistoryModel.LoadFreshMatchHistoryData(role, -1, this);
    }

    @Override
    public void LoadMatchSummary(long matchId) {
        if (view == null) {
            throw new IllegalStateException("Please set a view before calling start on this presenter");
        }

        matchHistoryModel.LoadCachedMatchSummary(matchId, this);
        matchHistoryModel.LoadFreshMatchSummary(matchId, this);
    }

    @Override
    public void setView(MatchHistoryTabContract.View view) {
        this.view = view;
    }

    @Override
    public void onMatchSummaryLoadedSuccessfully(MatchSummary matchSummary) {

    }

    @Override
    public void onError(Throwable e) {
        view.showMessage("An error occurred. Please try again.");
    }


    @Override
    public void onMatchListLoadedSuccessfully(MatchHistoryData matchHistoryData) {
        MatchHistoryAdapter matchHistoryAdapter = new MatchHistoryAdapter();
        matchHistoryAdapter.setData(matchHistoryData.getItems());
        view.setAdapter(matchHistoryAdapter);
    }

    @Override
    public void setRole(int role) {

    }
}
