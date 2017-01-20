package com.teamunemployment.lolanalytics.Base;

import android.content.Context;

import com.teamunemployment.lolanalytics.Contracts.PresenterContract;
import com.teamunemployment.lolanalytics.RESTService.RESTApiExecutor;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Josiah Kendall
 */
public class ModelTests {

    TabModel tabModel;
    RESTApiExecutor RESTApiExecutor;
    RealmExecutor realmExecutor;
    PresenterContract presenterContract;

    @Before
    public void init() {
        Context context = mock(Context.class);
        RESTApiExecutor = mock(RESTApiExecutor.class);
        realmExecutor = mock(RealmExecutor.class);
        tabModel = new TabModel(RESTApiExecutor, realmExecutor, context);
        presenterContract = mock(PresenterContract.class);
    }

    @Test
    public void TestThatWeCanFetchTheCorrectDataForTop() {
        tabModel.CreateLaneDataObservable(-1, Statics.TOP);
        verify(RESTApiExecutor, times(1)).GetTopStatsForSummoner(-1);
    }

    @Test
    public void TestThatWeCanFetchTheCorrectDataForJungle() {
        tabModel.CreateLaneDataObservable(-1, Statics.JUNGLE);
        verify(RESTApiExecutor, times(1)).GetJungleStatsForSummoner(-1);
    }

    @Test
    public void TestThatWeCanFetchTheCorrectDataForMid() {
        tabModel.CreateLaneDataObservable(-1, Statics.MID);
        verify(RESTApiExecutor, times(1)).GetMidStatsForSummoner(-1);
    }

    @Test
    public void TestThatWeCanFetchTheCorrectDataForADC() {
        tabModel.CreateLaneDataObservable(-1, Statics.ADC);
        verify(RESTApiExecutor, times(1)).GetAdcStatsForSummoner(-1);
    }

    @Test
    public void TestThatWeCanFetchTheCorrectDataForSupport() {
        tabModel.CreateLaneDataObservable(-1, Statics.SUPPORT);
        verify(RESTApiExecutor, times(1)).GetSupportStats(-1);
    }
}
