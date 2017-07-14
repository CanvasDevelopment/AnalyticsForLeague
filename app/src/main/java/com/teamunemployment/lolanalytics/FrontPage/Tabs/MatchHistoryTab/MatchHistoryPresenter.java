package com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab;

import android.util.Log;

import com.teamunemployment.lolanalytics.Data.Statics;
import com.teamunemployment.lolanalytics.Data.model.MatchIdWrapper;
import com.teamunemployment.lolanalytics.Data.model.MatchSummary;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.Cards.MatchHistoryCardViewContract;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.Model.MatchHistoryCardData;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.BarChartModel;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.TabContract;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * @author Josiah Kendall.
 */
public class MatchHistoryPresenter implements MatchHistoryTabContract.Presenter {

    private MatchHistoryInteractor matchHistoryInteractor;
    private TabContract.View view;
    private BarChartModel barChartModel;

    @Inject
    public MatchHistoryPresenter(MatchHistoryInteractor matchHistoryInteractor, BarChartModel barChartModel) {
        this.matchHistoryInteractor = matchHistoryInteractor;
        this.barChartModel = barChartModel;
    }

    @Override
    public void start() {
        LoadDataForRole(Statics.TOP, -1);
    }

    @Override
    public void handleError(Throwable e) {
        Log.d("MatchHistory", "Error: " + e.getMessage());
    }

    @Override
    public void restart() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void LoadDataForRole(int role, long summonerId) {

        if (view == null) {
            throw new IllegalStateException("Please set a view before calling start on this presenter");
        }

        matchHistoryInteractor.LoadCachedMatchHistoryData(role, -1, this);
        matchHistoryInteractor.LoadFreshMatchHistoryData(role, -1, this);
    }

    @Override
    public void LoadMatchSummary(long matchId) {
        if (view == null) {
            throw new IllegalStateException("Please set a view before calling start on this presenter");
        }

        matchHistoryInteractor.LoadCachedMatchSummary(matchId, this);
        matchHistoryInteractor.LoadFreshMatchSummary(matchId, this);
    }

    @Override
    public void setView(TabContract.View view) {
        this.view = view;
    }

    @Override
    public void onMatchSummaryLoadedSuccessfully(MatchSummary matchSummary) {

    }

    @Override
    public void onError(Throwable e) {
        Log.e("MatchHistory", "Error: " + e.getMessage());
        view.showMessage("An error occurred. Please try again.");
    }

    @Override
    public void onMatchListLoadedSuccessfully(ArrayList<MatchIdWrapper> matchHistoryData) {
        MatchHistoryAdapter matchHistoryAdapter = new MatchHistoryAdapter(this, barChartModel);

        matchHistoryAdapter.setData(matchHistoryData);
        view.setAdapter(matchHistoryAdapter);
    }

    @Override
    public void setRole(int role) {

    }

    /**
     * Load the card data.
     * @param id The match id for the data we want to load.
     * @param cardViewContract The card object to present the data back to.
     */
    @Override
    public void LoadCardData(long id, MatchHistoryCardViewContract cardViewContract) {
        matchHistoryInteractor.LoadMatchDetails(id, this, cardViewContract);
    }

    /**
     * Set our loaded card data back to the presenter for presenting to the card.
     * =====================
     * Note the cardViewContract being handed around. That is because we want to
     * maintain the same request/callback pattern that we use everywhere else for ease.
     * The reason we have to keep the instance is that we cannot reliably maintain a reference to that card
     * object any other way. The other option is to take the observables out of the interactor and process
     * them here in the presenter. I feel like this is the lesser of two evils.
     * @param matchHistoryCardData The card data to present.
     * @param cardViewContract The card view object to present to.
     */
    @Override
    public void SetLoadedCardData(MatchHistoryCardData matchHistoryCardData, MatchHistoryCardViewContract cardViewContract) {
        cardViewContract.setChampIcon("NOT_DONE" + matchHistoryCardData.getChampId() + ".png");
        cardViewContract.setChampName(matchHistoryCardData.getChampName());
        cardViewContract.setGraph1(matchHistoryCardData.getKills());
        cardViewContract.setGraph2(matchHistoryCardData.getCsTotal());
        cardViewContract.setGraph3(matchHistoryCardData.getCsFirstTen());
        cardViewContract.setGraph4(matchHistoryCardData.getCsSecondTen());

    }

}
