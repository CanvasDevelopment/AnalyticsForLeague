package com.teamunemployment.lolanalytics.Base;

import com.teamunemployment.lolanalytics.Contracts.ModelPresenterContract;
import com.teamunemployment.lolanalytics.RESTService.Api;

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
    Api api;
    RealmInterface realmInterface;
    ModelPresenterContract modelPresenterContract;

    @Before
    public void init() {
        api = mock(Api.class);
        realmInterface = mock(RealmInterface.class);
        baseModel = new BaseModel(api, realmInterface);
        modelPresenterContract = mock(ModelPresenterContract.class);
    }

    @Test
    public void TestThatWeCanFetchTheCorrectDataForTop() {
        baseModel.CreateLaneDataObservable(-1, Statics.TOP);
        verify(api, times(1)).GetTopStatsForSummoner(-1);
    }

    @Test
    public void TestThatWeCanFetchTheCorrectDataForJungle() {
        baseModel.CreateLaneDataObservable(-1, Statics.JUNGLE);
        verify(api, times(1)).GetJungleStatsForSummoner(-1);
    }

    @Test
    public void TestThatWeCanFetchTheCorrectDataForMid() {
        baseModel.CreateLaneDataObservable(-1, Statics.MID);
        verify(api, times(1)).GetMidStatsForSummoner(-1);
    }

    @Test
    public void TestThatWeCanFetchTheCorrectDataForADC() {
        baseModel.CreateLaneDataObservable(-1, Statics.ADC);
        verify(api, times(1)).GetAdcStatsForSummoner(-1);
    }

    @Test
    public void TestThatWeCanFetchTheCorrectDataForSupport() {
        baseModel.CreateLaneDataObservable(-1, Statics.SUPPORT);
        verify(api, times(1)).GetSupportStats(-1);
    }
}
