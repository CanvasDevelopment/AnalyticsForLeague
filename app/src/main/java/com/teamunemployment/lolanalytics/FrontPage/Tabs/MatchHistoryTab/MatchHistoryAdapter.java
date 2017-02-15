package com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamunemployment.lolanalytics.Data.model.MatchIdWrapper;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.BarChartModel;
import com.teamunemployment.lolanalytics.R;

import java.util.ArrayList;

/**
 * @author Josiah Kendall.
 *
 * Adapter for the Match History tab view.
 */
public class MatchHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<MatchIdWrapper> matchIds;

    private MatchHistoryBasePresenter matchHistoryBasePresenter;
    private BarChartModel barChartModel;

    public MatchHistoryAdapter(MatchHistoryBasePresenter matchHistoryBasePresenter, BarChartModel barChartModel) {
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
            throw new IllegalStateException("SetData() must be called before binding adapter");
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
}
