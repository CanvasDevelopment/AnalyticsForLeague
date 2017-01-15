package com.teamunemployment.lolanalytics.Base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;

import com.teamunemployment.lolanalytics.Jungle.Model.AdapterPojo;
import com.teamunemployment.lolanalytics.Jungle.PresentationLayer.BarChartCardView;
import com.teamunemployment.lolanalytics.R;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Adapter for the jungle recycler view.
 */
public class BaseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<AdapterPojo> pojos;
    private BasePresenter basePresenter;

    @Bind(R.id.chart) BarChart barChart;

    public BaseRecyclerAdapter(ArrayList<AdapterPojo> pojos, BasePresenter basePresenter) {
        this.pojos = pojos;
        this.basePresenter = basePresenter;
    }

    public BaseRecyclerAdapter(BasePresenter basePresenter) {
        this.pojos = new ArrayList<>();
        this.basePresenter = basePresenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        BarChartCardView cardView = new BarChartCardView(v, parent.getContext());
        return cardView;
    }

    public void AddItem(AdapterPojo pojo) {
        pojos.add(pojo);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // Inflate our jungle view.
        BarChartCardView cardView = (BarChartCardView) holder;
        AdapterPojo data = pojos.get(position);
        cardView.setBarChartData(data);
        cardView.setTitle(data.title);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return pojos.size();
    }


}
