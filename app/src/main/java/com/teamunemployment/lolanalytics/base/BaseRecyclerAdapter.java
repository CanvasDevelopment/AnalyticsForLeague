package com.teamunemployment.lolanalytics.Base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;

import com.teamunemployment.lolanalytics.Jungle.Model.AdapterPojo;
import com.teamunemployment.lolanalytics.Jungle.Model.BarChartCardModel;
import com.teamunemployment.lolanalytics.Jungle.Model.BarChartFactory;
import com.teamunemployment.lolanalytics.Jungle.PresentationLayer.BarChartCardView;
import com.teamunemployment.lolanalytics.R;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Adapter for the recycler view used in the tabs.
 */
public class BaseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<AdapterPojo> pojos;
    private BarChartCardModel barChartCardModel;

    @Inject
    public BaseRecyclerAdapter(BarChartCardModel barChartCardModel) {
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

        AdapterPojo data = pojos.get(position);
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


    public void SetData(ArrayList<AdapterPojo> adapterPojos) {
        this.pojos = adapterPojos;
    }
}
