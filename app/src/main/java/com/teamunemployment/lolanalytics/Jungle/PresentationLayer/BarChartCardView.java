package com.teamunemployment.lolanalytics.Jungle.PresentationLayer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.Chart;

/**
 * @author Josiah Kendall.
 * Not sure if this needs to be a specific chart class (e.g bar chart) It will probably work as a generic
 * chart view. Chart library seems to handle the polymorphism really well.
 */
public class BarChartCardView extends RecyclerView.ViewHolder implements ChartCardViewContract{

    private Context context;
    private RelativeLayout cardInner;

    public BarChartCardView(View itemView, Context context) {
        super(itemView);
        this.context = context;
        this.cardInner = (RelativeLayout) itemView;
    }

    public RelativeLayout getCardInner() {
        return cardInner;
    }


    @Override
    public void AddChart(Chart chart) {
        cardInner.addView(chart);
    }
}
