package com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamunemployment.lolanalytics.Data.model.MatchIdWrapper;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.Cards.MatchHistoryCardView;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatDefinition;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.BarChartModel;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.CardData;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.TabContract;
import com.teamunemployment.lolanalytics.R;

import java.util.ArrayList;

/**
 * @author Josiah Kendall.
 *
 * Adapter for the Match History tab view.
 */
public class MatchHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements TabContract.TabAdapter{

    private ArrayList<MatchIdWrapper> matchIds;

    private MatchHistoryPresenter matchHistoryBasePresenter;
    private BarChartModel barChartModel;

    public MatchHistoryAdapter(MatchHistoryPresenter matchHistoryBasePresenter, BarChartModel barChartModel) {
        this.matchHistoryBasePresenter = matchHistoryBasePresenter;
        this.barChartModel = barChartModel;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_history_card, parent, false);
        MatchHistoryCardView matchHistoryCardView = new MatchHistoryCardView(v,parent.getContext(), matchHistoryBasePresenter, barChartModel);
        return matchHistoryCardView;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Our card view instance
        MatchHistoryCardView cardView = (MatchHistoryCardView) holder;

        if (matchIds == null) {
            throw new IllegalStateException("setPlayerAnalysisAdapterData() must be called before binding adapter");
        }

        // Load our data
        cardView.loadData(matchIds.get(position).getMatchId());
    }

    @Override
    public int getItemCount() {
        return matchIds.size();
    }

    public void setData(ArrayList<MatchIdWrapper> matchIds) {
        this.matchIds = matchIds;
    }

    @Override
    public void setMatchHistoryAdapterData(ArrayList<MatchIdWrapper> matchIds) {
        this.matchIds = matchIds;
    }

    @Override
    public void setPlayerAnalysisAdapterData(ArrayList<StatDefinition> statDefinitions) {
        throw new IllegalStateException("This is a matchHistoryAdapter. Please use the appropriate method.");
    }

    @Override
    public void setStatComparisonAdapterData(ArrayList<CardData> cardDatas) {
        throw new IllegalStateException("This is a matchHistoryAdapter. Please use the appropriate method.");
    }
}
