package com.teamunemployment.lolanalytics.Jungle.PresentationLayer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;

import com.teamunemployment.lolanalytics.Jungle.Model.BarChartFactory;
import com.teamunemployment.lolanalytics.Jungle.Model.JungleAdapterPojo;
import com.teamunemployment.lolanalytics.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Adapter for the jungle recycler view.
 */
public class JungleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<JungleAdapterPojo> pojos;
    private JunglePresenter junglePresenter;

    @Bind(R.id.chart) BarChart barChart;

    public JungleAdapter(ArrayList<JungleAdapterPojo> pojos, JunglePresenter junglePresenter) {
        this.pojos = pojos;
        this.junglePresenter = junglePresenter;
    }

    public JungleAdapter(JunglePresenter junglePresenter) {
        this.pojos = new ArrayList<>();
        this.junglePresenter = junglePresenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        BarChartCardView cardView = new BarChartCardView(v, parent.getContext());
        return cardView;
    }

    public void AddItem(JungleAdapterPojo pojo) {
        pojos.add(pojo);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // Inflate our jungle view.
        BarChartCardView cardView = (BarChartCardView) holder;
        cardView.setBarChartData(pojos.get(position));
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
