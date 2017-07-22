package com.teamunemployment.lolanalytics.FrontPage.Tabs.AnalyseTab;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Josiah Kendall
 */

public class AnalyseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements AnalyseTabContract.Adapter{

    private AnalyseTabContract.Presenter presenter;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return presenter.GetFilterListSize();
    }

    @Override
    public void SetPresenter(AnalyseTabContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
