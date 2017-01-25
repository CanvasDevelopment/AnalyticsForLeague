package com.teamunemployment.lolanalytics.FrontPage.StatsComparisonTab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamunemployment.lolanalytics.FrontPage.StatsComparisonTab.BarChartCardView;
import com.teamunemployment.lolanalytics.StatsComparisonTab.Model.CardData;
import com.teamunemployment.lolanalytics.StatsComparisonTab.Model.BarChartCardModel;
import com.teamunemployment.lolanalytics.R;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * @author Josiah Kendall.
 *
 * Adapter for the recycler view used in the tabs.
 */
public class TabRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<CardData> pojos;
    private BarChartCardModel barChartCardModel;

    @Inject
    public TabRecyclerAdapter(BarChartCardModel barChartCardModel) {
        this.barChartCardModel = barChartCardModel;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        BarChartCardView cardView = new BarChartCardView(v, parent.getContext(), barChartCardModel);
        return cardView;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // Inflate our jungle view.
        BarChartCardView cardView = (BarChartCardView) holder;

        if (pojos == null) {
            throw new IllegalStateException("SetData() must be called before binding adapter");
        }

        CardData data = pojos.get(position);
        cardView.setBarChartData(data);
        cardView.setTitle(data.getTitle());
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return pojos.size();
    }


    public void SetData(ArrayList<CardData> cardDatas) {
        this.pojos = cardDatas;
    }
}