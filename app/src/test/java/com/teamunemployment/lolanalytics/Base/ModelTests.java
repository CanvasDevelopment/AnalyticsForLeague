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

    BaseModel baseModel;
    RESTApiExecutor RESTApiExecutor;
    RealmExecutor realmExecutor;
    PresenterContract presenterContract;

    @Before
    public void init() {
        Context context = mock(Context.class);
        RESTApiExecutor = mock(RESTApiExecutor.class);
        realmExecutor = mock(RealmExecutor.class);
        baseModel = new BaseModel(RESTApiExecutor, realmExecutor, context);
        presenterContract = mock(PresenterContract.class);
    }

    @Test
    public void TestThatWeCanFetchTheCorrectDataForTop() {
        baseModel.CreateLaneDataObservable(-1, Statics.TOP);
        verify(RESTApiExecutor, times(1)).GetTopStatsForSummoner(-1);
    }

    @Test
    public void TestThatWeCanFetchTheCorrectDataForJungle() {
        baseModel.CreateLaneDataObservable(-1, Statics.JUNGLE);
        verify(RESTApiExecutor, times(1)).GetJungleStatsForSummoner(-1);
    }

    @Test
    public void TestThatWeCanFetchTheCorrectDataForMid() {
        baseModel.CreateLaneDataObservable(-1, Statics.MID);
        verify(RESTApiExecutor, times(1)).GetMidStatsForSummoner(-1);
    }

    @Test
    public void TestThatWeCanFetchTheCorrectDataForADC() {
        baseModel.CreateLaneDataObservable(-1, Statics.ADC);
        verify(RESTApiExecutor, times(1)).GetAdcStatsForSummoner(-1);
    }

    @Test
    public void TestThatWeCanFetchTheCorrectDataForSupport() {
        baseModel.CreateLaneDataObservable(-1, Statics.SUPPORT);
        verify(RESTApiExecutor, times(1)).GetSupportStats(-1);
    }
}
