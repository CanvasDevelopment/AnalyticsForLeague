package com.teamunemployment.lolanalytics.Base;

import android.support.v7.widget.RecyclerView;

import com.teamunemployment.lolanalytics.Base.BaseRecyclerAdapter;
import com.teamunemployment.lolanalytics.Jungle.Model.BarChartCardModel;
import com.teamunemployment.lolanalytics.Jungle.Model.BarChartFactory;
import com.teamunemployment.lolanalytics.Jungle.PresentationLayer.BarChartCardView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * @author Josiah Kendall
 */

public class AdapterTests {


    @Before

    @Test
    public void TestThatWeCanSetDataToTheBarChart() {

    }

    @Test(expected = IllegalStateException.class)
    public void TestThatWeGetIllegalStateExceptionIfWeBindBeforeSettingData() {
        BaseRecyclerAdapter baseRecyclerAdapter = new BaseRecyclerAdapter(mock(BarChartCardModel.class));
        baseRecyclerAdapter.onBindViewHolder(mock(BarChartCardView.class), 1);
    }

}
