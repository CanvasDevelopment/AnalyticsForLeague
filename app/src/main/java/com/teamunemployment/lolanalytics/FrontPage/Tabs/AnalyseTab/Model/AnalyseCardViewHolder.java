package com.teamunemployment.lolanalytics.FrontPage.Tabs.AnalyseTab.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.teamunemployment.lolanalytics.FrontPage.Tabs.AnalyseTab.AnalyseAdapter;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.AnalyseTab.AnalysePresenter;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.AnalyseTab.AnalyseTabContract;

/**
 * Created by Josiah Kendall
 */

public class AnalyseCardViewHolder extends RecyclerView.ViewHolder implements AnalyseTabContract.CardView {

    private int position;
    private AnalysePresenter presenter;
    public AnalyseCardViewHolder(View itemView, AnalysePresenter analysePresenter) {
        super(itemView);
        this.presenter = analysePresenter;
    }

    @Override
    public void SetTitle(String title) {

    }

    @Override
    public void SetGraph(double enemyStat, double heroStat) {

    }

    @Override
    public void SetChange(double change) {

    }

    @Override
    public void SetItemPosition(int position) {
        this.position = position;
    }

    public void handleClick() {
        //
    }
}
