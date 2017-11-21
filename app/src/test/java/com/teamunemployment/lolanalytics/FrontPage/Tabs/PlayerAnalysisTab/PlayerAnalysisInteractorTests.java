package com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab;

import org.junit.BeforeClass;
import org.junit.Test;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.mock;

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
//        Context context = mock(Context.class); TODO
//        RESTApiExecutor restApiExecutor = mock(RESTApiExecutor.class);
//        StatCollection statCollection = new StatCollection();
//        Observable<StatCollection> statCollectionObservable = Observable.just(statCollection);
//        when(restApiExecutor.GetAnalysisStatCollection(anyInt(), anyLong(), anyInt())).thenReturn(statCollectionObservable);
//        final RealmExecutor realmExecutor = mock(RealmExecutor.class);
//
//        PlayerAnalysisPersistanceInteracter playerAnalysisPersistanceInteracter = new PlayerAnalysisPersistanceInteracter(restApiExecutor, realmExecutor, context);
//        Function<StatCollection, StatCollection> mappingFunc = collection -> {
//            Assert.fail("Not yet implemented");
//            return collection;
//        };
//
//        TestObserver<StatCollection> testObserver = new TestObserver<>();
//        playerAnalysisPersistanceInteracter.LoadStatAnalysisCardObjects(1, -1, 1, testObserver);
    }


}
