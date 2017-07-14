package com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamunemployment.lolanalytics.Data.model.MatchIdWrapper;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatDefinition;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.BarChartModel;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.CardData;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.TabContract;
import com.teamunemployment.lolanalytics.R;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * @author Josiah Kendall.
 *
 * Adapter for the recycler view used in the tabs.
 */
public class TabRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements TabContract.TabAdapter{

    private ArrayList<CardData> pojos;
    private BarChartModel barChartModel;

    @Inject
    public TabRecyclerAdapter(BarChartModel barChartModel) {
        this.barChartModel = barChartModel;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        BarChartCardView cardView = new BarChartCardView(v, parent.getContext(), barChartModel);
        return cardView;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // Inflate our jungle view.
        BarChartCardView cardView = (BarChartCardView) holder;

        if (pojos == null) {
            throw new IllegalStateException("setPlayerAnalysisAdapterData() must be called before binding adapter");
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

    @Override
    public void setMatchHistoryAdapterData(ArrayList<MatchIdWrapper> matchIds) {
        throw new IllegalStateException("This is a StatCOmparisonAdapter. Please use the appropriate method.");
    }

    @Override
    public void setPlayerAnalysisAdapterData(ArrayList<StatDefinition> statDefinitions) {
        throw new IllegalStateException("This is a StatComparisonAdapter. Please use the appropriate method.");
    }

    @Override
    public void setStatComparisonAdapterData(ArrayList<CardData> cardDatas) {
        this.pojos = cardDatas;
    }
}
