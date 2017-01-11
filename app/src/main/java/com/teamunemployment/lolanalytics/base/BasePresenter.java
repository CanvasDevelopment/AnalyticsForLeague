package com.teamunemployment.lolanalytics.Base;

import com.teamunemployment.lolanalytics.Jungle.Contracts.ModelPresenterContract;
import com.teamunemployment.lolanalytics.Jungle.Model.AdapterPojo;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * @author Josiah Kendall.
 */
public class BasePresenter implements ModelPresenterContract {

    private ViewFragmentContract jungleView;
    private BaseModel baseModel;
    private BaseRecyclerAdapter baseRecyclerAdapter;

    @Inject
    public BasePresenter(BaseModel baseModel) {
        this.baseModel = baseModel;
    }

    /**
     * Trigger the loading
     */
    @Override
    public void start(int lane) {
        // Load data
        baseModel.getCardPojos(-1, this, lane);
    }

    /**
     * Set our current view, so that we can interact with it. This method also starts the loading, and
     * construction of the recycler view.
     * @param jungleView
     */
    @Override
    public void setView(ViewFragmentContract jungleView, int lane) {
        this.jungleView = jungleView;
        start(lane);
    }


    public void showMessageToUser(String s) {
        jungleView.showMessage(s);
    }

    @Override
    public void addStatToList(AdapterPojo adapterPojo) {
        //baseRecyclerAdapter.AddItem(adapterPojo);
        //baseRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void addDataToAdapter(ArrayList<AdapterPojo> adapterPojos) {
        baseRecyclerAdapter = new BaseRecyclerAdapter(adapterPojos, this);
        jungleView.setJungleAdapter(baseRecyclerAdapter);
    }
}
