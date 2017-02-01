package com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.teamunemployment.lolanalytics.Data.model.MatchSummary;

import java.util.ArrayList;

/**
 * @author Josiah Kendall.
 */
public class MatchHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<MatchSummary> matchSummaries;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setData(ArrayList<MatchSummary> matchSummaries) {
        this.matchSummaries = matchSummaries;
    }
}
