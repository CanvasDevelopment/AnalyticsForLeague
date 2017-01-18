package com.teamunemployment.lolanalytics.Base;

import com.teamunemployment.lolanalytics.Data.Data;
import com.teamunemployment.lolanalytics.Contracts.ModelPresenterContract;
import com.teamunemployment.lolanalytics.Jungle.Model.AdapterPojo;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author Josiah Kendall.
 * Presenter for the tabs.
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

    /**
     * Trigger the loading, from both the cache and network.
     */
    @Override
    public void start(int lane) {
        // Load data
        Observable<Data> cachedDataObservable = baseModel.CreateCachedDataObservable(-1, lane);
        cachedDataObservable.
                // This is bad TODO find a solution
                subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Data>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Data data) {
                        addDataToAdapter(new ArrayList<AdapterPojo>(data.getItems()));
                    }
                });

        Observable<Data> dataObservable = baseModel.CreateLaneDataObservable(-1, lane);
        // dataObservable.mergeWith(cachedDataObservable);
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
