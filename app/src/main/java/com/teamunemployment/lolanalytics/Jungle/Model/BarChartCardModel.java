package com.teamunemployment.lolanalytics.Jungle.Model;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Josiah Kendall.
 *
 * This is the model for our Adapter
 */

public class BarChartCardModel {

    private BarChartFactory barChartFactory;

    @Inject
    public BarChartCardModel(BarChartFactory barChartFactory) {
        this.barChartFactory = barChartFactory;
    }

    public BarDataSet FetchBarDataSet(CardData cardData) {
        // We have our bar chart. Now we need to add stuff to it.
        List<BarEntry> entries = new ArrayList<>();

        // Create an entry for the user, and the enemy info. Add them to the chart entry list.
        BarEntry me = barChartFactory.GenerateBarEntry(0f, new Float(cardData.getFriendlyStats()));
        BarEntry them = barChartFactory.GenerateBarEntry(1f, new Float(cardData.getEnemyStats()));
        entries.add(me);
        entries.add(them);

        // Create dataset with specified colors. We dont have a title for our chart, because the title goes in an ugly place, so we will use our own later.
        BarDataSet dataSet = barChartFactory.createBarChartDataSet(entries, "");
        dataSet.setValueTextSize(14f);
        return dataSet;
    }

    public BarData FetchBarData(BarDataSet barDataSet) {


        // Finally, add our data to the chart.
        BarData barData = barChartFactory.GenerateBarDataUsingDataSet(barDataSet);
        return barData;
    }
}
