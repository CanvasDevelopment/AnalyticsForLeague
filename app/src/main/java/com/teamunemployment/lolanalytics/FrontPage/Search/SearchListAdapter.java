package com.teamunemployment.lolanalytics.FrontPage.Search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamunemployment.lolanalytics.Data.model.Champ;
import com.teamunemployment.lolanalytics.FrontPage.Search.Model.ChampSearchCardView;
import com.teamunemployment.lolanalytics.FrontPage.Search.Model.OffSetViewHolder;
import com.teamunemployment.lolanalytics.R;
import com.teamunemployment.lolanalytics.Utils.Constant;

import java.util.ArrayList;

/**
 * Created by Josiah Kendall
 */

public class SearchListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Champ> champs;
    private SearchPresenter searchPresenter;

    public SearchListAdapter(SearchPresenter searchPresenter) {
        this.searchPresenter = searchPresenter;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return Constant.ViewType.OFFSET_VIEW;
        }
        return Constant.ViewType.STANDARD_VIEW;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Constant.ViewType.OFFSET_VIEW) {
            // set up offset
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.offset_champ_view, parent, false);
            return new OffSetViewHolder(v);
        }

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.champ_search_card, parent, false);
        return new ChampSearchCardView(v, searchPresenter, parent.getContext());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof  OffSetViewHolder) {
            return;
        }

        if (champs == null) {
            throw new IllegalStateException("Champs array has not been set. SetData() must be called before views are bound");
        }

        Champ champ = champs.get(position);
        ChampSearchCardView champSearchCardView = (ChampSearchCardView) holder;
        champSearchCardView.SetChamp(champ);
    }

    public void SetData(ArrayList<Champ> champs) {
        this.champs = champs;
    }

    @Override
    public int getItemCount() {
        return champs.size();
    }
}
