package com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab;

import android.content.Context;

import com.github.mikephil.charting.data.Entry;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatCollection;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatPoint;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.Model.StatSummary;

import org.junit.Test;

import java.util.ArrayList;

import io.reactivex.Observer;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Josiah Kendall
 */

public class PlayerAnalysisPresenterTests {

    @Test
    public void Start_LoadsStatData() {
        Context context = mock(Context.class);
        PlayerAnalysisPersistanceInteracter playerAnalysisPersistanceInteracter = mock(PlayerAnalysisPersistanceInteracter.class);
        PlayerAnalysisPresenter playerAnalysisPresenter = new PlayerAnalysisPresenter(playerAnalysisPersistanceInteracter, context);
        PlayerAnalysisView view = mock(PlayerAnalysisView.class);
        playerAnalysisPresenter.setView(view);
        playerAnalysisPresenter.setRole(1);
        playerAnalysisPresenter.start();
        verify(playerAnalysisPersistanceInteracter, times(1)).LoadStatTypes(anyInt(), anyLong(), any(Observer.class));
    }

    @Test(expected = IllegalStateException.class)
    public void Start_BeforeSettingViewCausesIllegalStateExceptionError() {
        Context context = mock(Context.class);

        PlayerAnalysisPersistanceInteracter playerAnalysisPersistanceInteracter = mock(PlayerAnalysisPersistanceInteracter.class);
        PlayerAnalysisPresenter playerAnalysisPresenter = new PlayerAnalysisPresenter(playerAnalysisPersistanceInteracter, context);
        playerAnalysisPresenter.start();
    }

    @Test
    public void OnStatCollectionLoadedImpl_TriggersAdapterToBeSet() {
        Context context = mock(Context.class);
        PlayerAnalysisPersistanceInteracter playerAnalysisPersistanceInteracter = mock(PlayerAnalysisPersistanceInteracter.class);
        PlayerAnalysisPresenter playerAnalysisPresenter = new PlayerAnalysisPresenter(playerAnalysisPersistanceInteracter, context);
        PlayerAnalysisView view = mock(PlayerAnalysisView.class);
        playerAnalysisPresenter.setView(view);
        StatSummary statSummary = new StatSummary();
        statSummary.setId(1);
        statSummary.setGoalvalue(12.32);
        statSummary.setStatName("TEST_STAT");
        statSummary.setHasGoal(true);
        StatCollection statCollection = new StatCollection();
        ArrayList<Entry> statSummaries = new ArrayList<>();
        statSummaries.add(new Entry());
        statCollection.setCollection(statSummaries, statSummaries);

        playerAnalysisPresenter.onStatCollectionLoaded(statCollection);
       // verify(view, times(1)).setStatCollectionData(statCollection);
    }

    @Test
    public void OnStatCollectionFailedImpl_TriggersCorrectResponse() {
        Context context = mock(Context.class);
        PlayerAnalysisPersistanceInteracter playerAnalysisPersistanceInteracter = mock(PlayerAnalysisPersistanceInteracter.class);
        PlayerAnalysisPresenter playerAnalysisPresenter = new PlayerAnalysisPresenter(playerAnalysisPersistanceInteracter, context);
        PlayerAnalysisView view = mock(PlayerAnalysisView.class);
        playerAnalysisPresenter.setView(view);
        playerAnalysisPresenter.onStatCollectionFailed("FAILED");
        verify(view, times(1)).showMessage("FAILED");
    }
}
