package com.teamunemployment.lolanalytics.front_page.Tabs.StatsComparisonTab;

import android.content.Context;

import com.teamunemployment.lolanalytics.data.Statics;
import com.teamunemployment.lolanalytics.io.RESTApiExecutor;
import com.teamunemployment.lolanalytics.io.RealmExecutor;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
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
    TabModelContract tabModelContract;

    @Before
    public void init() {
        Context context = mock(Context.class);
        RESTApiExecutor = mock(RESTApiExecutor.class);
        realmExecutor = mock(RealmExecutor.class);
        tabModel = new TabModel(RESTApiExecutor, realmExecutor, context);
        tabModelContract = mock(TabModelContract.class);
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

    @Test
    public void TestThatWeFetchCachedDataForTop() {
        // TODO fix this.
//        Observable<Data> dataObservable = tabModel.CreateCachedDataObservable(-1, Statics.TOP);
//        dataObservable.subscribe();
//        verify(realmExecutor, times(1)).FindDataForRole(anyInt(), anyLong(), any(Realm.class));
    }

    @Test
    public void TestThatWeAreCachingTheDataThatWeRecieve() {

    }
}