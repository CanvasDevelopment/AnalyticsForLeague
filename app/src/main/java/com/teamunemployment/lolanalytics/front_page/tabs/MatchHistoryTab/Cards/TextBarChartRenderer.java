package com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards;

import android.graphics.Canvas;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.renderer.BarChartRenderer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.List;

public class TextBarChartRenderer extends BarChartRenderer {

    public TextBarChartRenderer(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
    }

    @Override
    public void drawValues(Canvas c) {
        List<IBarDataSet> dataSets = mChart.getBarData().getDataSets();

        final float valueOffsetPlus = Utils.convertDpToPixel(22f);
        float negOffset;

        for (int i = 0; i < mChart.getBarData().getDataSetCount(); i++) {

            IBarDataSet dataSet = dataSets.get(i);
            applyValueTextStyle(dataSet);
            float valueTextHeight = Utils.calcTextHeight(mValuePaint, "8");
            negOffset = valueTextHeight + valueOffsetPlus;

            BarBuffer buffer = mBarBuffers[i];

            float left;
            float right;
            float top;
            float bottom;

            for (int j = 0; j < buffer.buffer.length * mAnimator.getPhaseX(); j += 4) {

                left = buffer.buffer[j];
                right = buffer.buffer[j + 2];
                top = buffer.buffer[j + 1];
                bottom = buffer.buffer[j + 3];

                float x = (left + right) / 2f;

                if (!mViewPortHandler.isInBoundsRight(x))
                    break;

                if (!mViewPortHandler.isInBoundsY(top) || !mViewPortHandler.isInBoundsLeft(x))
                    continue;

                BarEntry entry = dataSet.getEntryForIndex(j / 4);
                float val = entry.getY();

                if(val > 0) {
                    drawValue(c, dataSet.getValueFormatter(), val, entry, i, x,
                            (bottom + negOffset),
                            dataSet.getValueTextColor(j / 4));
                }
            }
        }

    }
}
