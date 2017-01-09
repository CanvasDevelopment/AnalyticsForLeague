package com.teamunemployment.lolanalytics.base;

import com.teamunemployment.lolanalytics.Jungle.Contracts.ModelPresenterContract;
import com.teamunemployment.lolanalytics.base.BaseRecyclerAdapter;
import com.teamunemployment.lolanalytics.base.ViewFragmentContract;
import com.teamunemployment.lolanalytics.Jungle.Model.JungleAdapterPojo;
import com.teamunemployment.lolanalytics.base.BaseModel;

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
    public void start() {
        // TODO shared preferences?
        String placeholder = "kloin";

        // Load data
        baseModel.getCardPojos(placeholder, "TOP", this);
    }

    /**
     * Set our current view, so that we can interact with it. This method also starts the loading, and
     * construction of the recycler view.
     * @param jungleView
     */
    @Override
    public void setView(ViewFragmentContract jungleView) {
        this.jungleView = jungleView;
        start();
    }


    public void showMessageToUser(String s) {
        jungleView.showMessage(s);
    }

    @Override
    public void addStatToList(JungleAdapterPojo jungleAdapterPojo) {
        //baseRecyclerAdapter.AddItem(jungleAdapterPojo);
        //baseRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void addDataToAdapter(ArrayList<JungleAdapterPojo> adapterPojos) {
        baseRecyclerAdapter = new BaseRecyclerAdapter(adapterPojos, this);
        jungleView.setJungleAdapter(baseRecyclerAdapter);
    }
}
