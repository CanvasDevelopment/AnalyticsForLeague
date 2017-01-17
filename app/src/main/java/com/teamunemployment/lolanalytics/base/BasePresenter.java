package com.teamunemployment.lolanalytics.Base;

import com.teamunemployment.lolanalytics.Data.Data;
import com.teamunemployment.lolanalytics.Jungle.Contracts.ModelPresenterContract;
import com.teamunemployment.lolanalytics.Jungle.Model.AdapterPojo;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Josiah Kendall.
 */
public class BasePresenter implements ModelPresenterContract {

    private ViewFragmentContract view;
    private BaseModel baseModel;
    private BaseRecyclerAdapter baseRecyclerAdapter;

    @Inject
    public BasePresenter(BaseModel baseModel, BaseRecyclerAdapter baseRecyclerAdapter) {
        this.baseModel = baseModel;
        this.baseRecyclerAdapter = baseRecyclerAdapter;
    }

    public BasePresenter(BaseModel baseModel) {
        this.baseRecyclerAdapter = null;
        this.baseModel = baseModel;
    }

    /**
     * Trigger the loading
     */
    @Override
    public void start(int lane) {
        // Load data
        Observable<Data> dataObservable = baseModel.CreateLaneDataObservable(-1, lane);
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
        start(lane);
    }

    @Override
    public void handleError(Throwable e) {

    }


    public void showMessageToUser(String s) {
        view.showMessage(s);
    }

    @Override
    public void addDataToAdapter(ArrayList<AdapterPojo> adapterPojos) {
        baseRecyclerAdapter.SetData(adapterPojos);
        view.setJungleAdapter(baseRecyclerAdapter);
    }
}
