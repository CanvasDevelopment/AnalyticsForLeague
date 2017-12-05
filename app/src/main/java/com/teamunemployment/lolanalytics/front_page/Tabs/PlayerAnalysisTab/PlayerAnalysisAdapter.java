package com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamunemployment.lolanalytics.data.model.MatchIdWrapper;
import com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab.Model.StatDefinition;
import com.teamunemployment.lolanalytics.front_page.Tabs.StatsComparisonTab.Model.CardData;
import com.teamunemployment.lolanalytics.front_page.Tabs.TabContract;
import com.teamunemployment.lolanalytics.R;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * @author Josiah Kendall
 */

public class PlayerAnalysisAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements TabContract.TabAdapter {
    private ArrayList<StatDefinition> statDefinitions;

    private Context context;
    private PlayerAnalysisPresenter playerAnalysisBasePresenter;

    @Inject
    public PlayerAnalysisAdapter(Context context, PlayerAnalysisPresenter playerAnalysisBasePresenter ) {
        this.context = context;
        this.playerAnalysisBasePresenter = playerAnalysisBasePresenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.analysis_list_item, parent, false);
        AnalysisCardView analysisCardView = new AnalysisCardView(v, context,playerAnalysisBasePresenter);
        return analysisCardView;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // Inflate our jungle view.
        AnalysisCardView cardView = (AnalysisCardView) holder;

        if (statDefinitions == null) {
            throw new IllegalStateException("setPlayerAnalysisAdapterData() must be called before binding adapter");
        }
        cardView.SetStatDefinition(statDefinitions.get(position));
        cardView.LoadFaceData(statDefinitions.get(position).getStatId());
    }

    @Override
    public long getItemId(int i) {
        return statDefinitions.get(i).getStatId();
    }

    @Override
    public int getItemCount() {
        return statDefinitions.size();
    }



    @Override
    public void setMatchHistoryAdapterData(ArrayList<MatchIdWrapper> matchIds) {
        throw new IllegalStateException("This is a PlayerAnalysisAdapter intance. Use appropriate value" +
                "setter.");
    }

    @Override
    public void setPlayerAnalysisAdapterData(ArrayList<StatDefinition> statDefinitions) {
        this.statDefinitions = statDefinitions;
    }

    @Override
    public void setStatComparisonAdapterData(ArrayList<CardData> cardDatas) {
        throw new IllegalStateException("This is a PlayerAnalysisAdapter intance. Use appropriate value" +
                "setter.");
    }
}
