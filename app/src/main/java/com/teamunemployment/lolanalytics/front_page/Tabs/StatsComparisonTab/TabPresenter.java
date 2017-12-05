package com.teamunemployment.lolanalytics.front_page.Tabs.StatsComparisonTab;

import com.teamunemployment.lolanalytics.data.model.Data;
import com.teamunemployment.lolanalytics.front_page.Tabs.StatsComparisonTab.Model.CardData;
import com.teamunemployment.lolanalytics.front_page.Tabs.TabContract;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Josiah Kendall.
 * Presenter for the tabs.
 */
public class TabPresenter implements TabModelContract.Presenter {

    private static final String TAG = "Presenter";
    private TabContract.View view;
    private TabModel tabModel;
    private TabContract.TabAdapter tabRecyclerAdapter;
    private int lane = -1;

    @Inject
    public TabPresenter(TabModel tabModel, TabContract.TabAdapter tabRecyclerAdapter) {
        this.tabModel = tabModel;
        this.tabRecyclerAdapter = tabRecyclerAdapter;
    }

    /**
     * Set our current view, so that we can interact with it. This method also starts the loading, and
     * construction of the recycler view.
     * @param view The view we are going to be setting
     */
    @Override
    public void setView(TabContract.View view, int lane) {
        if (lane == -1 || lane > 4) {
            throw new IllegalStateException("Illegal role value given. Please set a value between 1 and 4 before " +
                    "the view is constructed using setRole(int)");
        }

        this.view = view;
        this.lane = lane;
        // Now we have a view to present to, we can start loading.
        start();
    }

    @Override
    public void handleError(Throwable e) {
        // showMessageToUser("An error occurred while loading fresh value. Please check you have an internet connection and retry.");
       // view.setErrorMessage("An error occured while loading. Please check that you have an internet connection.");
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


    public void showMessageToUser(String s) {
        view.showMessage(s);
    }

    @Override
    public void addDataToAdapter(ArrayList<CardData> cardDatas) {
        tabRecyclerAdapter.setStatComparisonAdapterData(cardDatas);
        view.setLoadingVisible(false);
        view.setAdapter(tabRecyclerAdapter);
    }

    @Override
    public void start() {

        if (view == null) {
            throw new IllegalStateException("Please set a view before calling start on this presenter");
        }

        Observable<Data> cachedDataObservable = tabModel.CreateCachedDataObservable(-1, lane);
        // subscribes to the cached value in here to trigger the realm query.
        tabModel.FetchCacheData(this, cachedDataObservable);

        Observable<Data> dataObservable = tabModel.CreateLaneDataObservable(-1, lane);
        // subscribes to the remote value in here to trigger the network request..
        tabModel.FetchData(this, dataObservable);
    }
}
