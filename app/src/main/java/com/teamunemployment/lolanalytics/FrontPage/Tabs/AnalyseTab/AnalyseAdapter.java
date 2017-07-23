package com.teamunemployment.lolanalytics.FrontPage.Tabs.AnalyseTab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.teamunemployment.lolanalytics.FrontPage.Tabs.AnalyseTab.Model.AnalyseCardViewHolder;
import com.teamunemployment.lolanalytics.R;

/**
 * Created by Josiah Kendall
 */

public class AnalyseAdapter extends RecyclerView.Adapter<AnalyseCardViewHolder>
        implements AnalyseTabContract.Adapter{

    private AnalyseTabContract.Presenter presenter;

    @Override
    public AnalyseCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AnalyseCardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.analyse_card_item, parent, false), presenter);
    }

    @Override
    public void onBindViewHolder(AnalyseCardViewHolder holder, int position) {
        presenter.OnCardBinding(holder, position);
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
