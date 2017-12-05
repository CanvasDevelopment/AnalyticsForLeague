package com.teamunemployment.lolanalytics.front_page.Tabs.CoachTab;

import android.content.Context;

import com.teamunemployment.lolanalytics.front_page.Tabs.CoachTab.Model.WinRateResult;

import java.util.ArrayList;

/**
 * @author Josiah Kendall
 */

public class CoachPresenter implements CoachContract.Presenter {


    private CoachInteractor interactor;
    private CoachContract.View view;
    private Context context;
    private CoachHorizontalScrollerAdapter coachHorizontalScrollerAdapter;

    public CoachPresenter(CoachInteractor interactor, Context context, CoachHorizontalScrollerAdapter coachHorizontalScrollerAdapter) {
        this.interactor = interactor;
        this.interactor.setPresenter(this);
        this.context = context;
        this.coachHorizontalScrollerAdapter = coachHorizontalScrollerAdapter;
    }

    @Override
    public void startLoadInfo(long userId) {
        if (view == null) {
            throw new IllegalStateException("View not set. Set view using the setView method before calling start");
        }
        LoadWinRates(userId);
    }

    @Override
    public void setView(CoachContract.View view) {
        this.view = view;
    }

    public void LoadWinRates(long userId) {
        interactor.LoadWinRates(userId);
        // Load
    }

    /**
     * callback method to set the winrates once loaded.
     * @param winRates
     */
    public void setWinRates(WinRateResult winRates) {
        view.setWinRateGraph(winRates.getGameWinRate(), winRates.getLaneWinRate());
    }

    public void LoadCards(long userId) {
        ArrayList<Integer> ids = interactor.FetchCardIds(userId);

        coachHorizontalScrollerAdapter.setIds(ids);
        view.setRecylcerViewAdapter(coachHorizontalScrollerAdapter);
    }


    @Override
    public void start() {

    }

    @Override
    public void handleError(Throwable e) {

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
}
