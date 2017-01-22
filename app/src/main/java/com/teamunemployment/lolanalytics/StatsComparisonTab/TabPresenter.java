package com.teamunemployment.lolanalytics.StatsComparisonTab;

import android.util.Log;

import com.teamunemployment.lolanalytics.BaseViewContract;
import com.teamunemployment.lolanalytics.Data.model.Data;
import com.teamunemployment.lolanalytics.StatsComparisonTab.Model.CardData;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Josiah Kendall.
 * Presenter for the tabs.
 */
public class TabPresenter implements TabContract.Presenter {

    private static final String TAG = "Presenter";
    private TabContract.View view;
    private TabModel tabModel;
    private TabRecyclerAdapter tabRecyclerAdapter;
    private int lane = -1;

    @Inject
    public TabPresenter(TabModel tabModel, TabRecyclerAdapter tabRecyclerAdapter) {
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
        Log.e(TAG, e.getMessage());
    }


    public void showMessageToUser(String s) {
        view.showMessage(s);
    }

    @Override
    public void addDataToAdapter(ArrayList<CardData> cardDatas) {
        tabRecyclerAdapter.SetData(cardDatas);
        view.setAdapter(tabRecyclerAdapter);
    }

    @Override
    public void start() {
        Observable<Data> cachedDataObservable = tabModel.CreateCachedDataObservable(-1, lane);
        // subscribes to the cached data in here to trigger the realm query.
        tabModel.FetchCacheData(this, cachedDataObservable);

        Observable<Data> dataObservable = tabModel.CreateLaneDataObservable(-1, lane);
        // subscribes to the remote data in here to trigger the network request..
        tabModel.FetchData(this, dataObservable);
    }
}
