package com.teamunemployment.lolanalytics.Base;

import android.util.Log;

import com.teamunemployment.lolanalytics.Data.Data;
import com.teamunemployment.lolanalytics.Contracts.PresenterContract;
import com.teamunemployment.lolanalytics.Jungle.Model.CardData;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Josiah Kendall.
 * Presenter for the tabs.
 */
public class BasePresenter implements PresenterContract {

    private static final String TAG = "Presenter";
    private ViewFragmentContract view;
    private BaseModel baseModel;
    private BaseRecyclerAdapter baseRecyclerAdapter;

    @Inject
    public BasePresenter(BaseModel baseModel, BaseRecyclerAdapter baseRecyclerAdapter) {
        this.baseModel = baseModel;
        this.baseRecyclerAdapter = baseRecyclerAdapter;
    }

    /**
     * Trigger the loading, from both the cache and network.
     */
    @Override
    public void start(int lane) {
        // The data we need. Cached first, then replaced by fresh.
        Observable<Data> cachedDataObservable = baseModel.CreateCachedDataObservable(-1, lane);
        // subscribes to the cached data in here to trigger the realm query.
        baseModel.FetchCacheData(this, cachedDataObservable);
        Observable<Data> dataObservable = baseModel.CreateLaneDataObservable(-1, lane);
        // subscribes to the remote data in here to trigger the network request..
        baseModel.FetchData(this, dataObservable);
    }

    /**
     * Set our current view, so that we can interact with it. This method also starts the loading, and
     * construction of the recycler view.
     * @param view The view we are going to be setting
     */
    @Override
    public void setView(ViewFragmentContract view, int lane) {
        if (lane == -1 || lane > 4) {
            throw new IllegalStateException("Illegal role value given. Please set a value between 1 and 4 before " +
                    "the view is constructed using setRole(int)");
        }
        this.view = view;
        // Now we have a view to present to, we can start loading.
        start(lane);
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
        baseRecyclerAdapter.SetData(cardDatas);
        view.setJungleAdapter(baseRecyclerAdapter);
    }
}
