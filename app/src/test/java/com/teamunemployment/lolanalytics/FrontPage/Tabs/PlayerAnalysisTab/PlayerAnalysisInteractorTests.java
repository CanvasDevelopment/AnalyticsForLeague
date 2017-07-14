package com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab;

import android.content.Context;

import com.teamunemployment.lolanalytics.Data.RESTApiExecutor;
import com.teamunemployment.lolanalytics.Data.RealmExecutor;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatCollection;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Josiah Kendall
 */

public class PlayerAnalysisInteractorTests {

    /**
     * Required to avoid "Method getMainLooper in android.os.Looper not mocked" error.
     */
    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline());
    }

    @Test
    public void LoadStatAnalysisCardObects_CachesToRealmDb() {
        Context context = mock(Context.class);
        RESTApiExecutor restApiExecutor = mock(RESTApiExecutor.class);
        StatCollection statCollection = new StatCollection();
        Observable<StatCollection> statCollectionObservable = Observable.just(statCollection);
        when(restApiExecutor.GetAnalysisStatCollection(anyInt(), anyLong(), anyInt())).thenReturn(statCollectionObservable);
        final RealmExecutor realmExecutor = mock(RealmExecutor.class);

        PlayerAnalysisPersistanceInteracter playerAnalysisPersistanceInteracter = new PlayerAnalysisPersistanceInteracter(restApiExecutor, realmExecutor, context);
        Function<StatCollection, StatCollection> mappingFunc = collection -> {
            Assert.fail("Not yet implemented");
            return collection;
        };

        TestObserver<StatCollection> testObserver = new TestObserver<>();
        playerAnalysisPersistanceInteracter.LoadStatAnalysisCardObjects(1, -1, 1, testObserver);
    }


}
