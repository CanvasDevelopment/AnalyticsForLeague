package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.AnalyseTabContract;
import com.teamunemployment.lolanalytics.R;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

/**
 * Created by Josiah Kendall
 *
 * This is the view item for
 */

public class AnalyseCardViewHolder extends RecyclerView.ViewHolder implements AnalyseTabContract.CardView {

    private int position;
    private AnalyseTabContract.Presenter presenter;

    @BindView(R.id.performance_pie) PieChart pieChart;
    @BindView(R.id.analyse_card_title) TextView titleTextView;
    @BindView(R.id.performance_change) TextView performanceChange;

    public AnalyseCardViewHolder(View itemView, AnalyseTabContract.Presenter analysePresenter) {
        super(itemView);
        ButterKnife.bind(this ,itemView);
        this.presenter = analysePresenter;
    }

    @Override
    public void SetTitle(String title) {
        titleTextView.setText(title);
    }

    @Override
    public void SetGraph(float enemyStat, float heroStat) {
        // set the performance pie here
        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(enemyStat, "Opponent performance"));
        entries.add(new PieEntry(heroStat, "My performance"));


        PieDataSet set = new PieDataSet(entries, "");
        int[] MATERIAL_COLORS = {
                rgb("#FF3D00"),rgb("#00E676")
        };
        set.setColors(MATERIAL_COLORS);
        set.setDrawValues(false);
        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.setDrawMarkers(false);
        pieChart.setUsePercentValues(false);
        pieChart.setDrawSliceText(false);
        pieChart.setHighlightPerTapEnabled(false);
        Float floater = heroStat;

        pieChart.setCenterText(Integer.toString(floater.intValue()).concat("%"));
        pieChart.setCenterTextColor(R.color.colorAccent);
        pieChart.setDrawEntryLabels(false);

        // Dont want to display a description.
        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);

        // Customize legend.
        pieChart.getLegend().setForm(Legend.LegendForm.CIRCLE);
        pieChart.getLegend().setEnabled(false);
        pieChart.invalidate();

    }

    @Override
    public void SetChange(double change) {
        performanceChange.setText(Double.toString(change));
    }

    @Override
    public void SetItemPosition(int position) {
        this.position = position;
    }

    @OnClick(R.id.root_analyse_item)
    public void handleClick() {
        presenter.HandleItemClick(position);
    }
}
