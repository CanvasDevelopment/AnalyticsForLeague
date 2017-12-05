package com.teamunemployment.lolanalytics.front_page.Tabs.CoachTab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * @author Josiah Kendall
 */

public class CoachHorizontalScrollerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private CoachPresenter presenter;
    private ArrayList<Integer> cardIds;
    public CoachHorizontalScrollerAdapter(Context context) {
        this.context = context;
    }

    public void setPresenter(CoachPresenter coachPresenter) {
        this.presenter = coachPresenter;
    }
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

    public void setIds(ArrayList<Integer> ids) {
        this.cardIds = ids;
    }

}
