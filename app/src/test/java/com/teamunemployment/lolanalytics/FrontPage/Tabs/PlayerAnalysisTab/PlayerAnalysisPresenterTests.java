package com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab;

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

        PlayerAnalysisPersistanceInteracter playerAnalysisPersistanceInteracter = mock(PlayerAnalysisPersistanceInteracter.class);
        PlayerAnalysisBasePresenter playerAnalysisPresenter = new PlayerAnalysisBasePresenter(playerAnalysisPersistanceInteracter);
        PlayerAnalysisView view = mock(PlayerAnalysisView.class);
        playerAnalysisPresenter.setView(view);
        playerAnalysisPresenter.start();
        verify(playerAnalysisPersistanceInteracter, times(1)).LoadStatAnalysisCardObjects(anyInt(), anyLong(), any(Observer.class));
    }

    @Test(expected = IllegalStateException.class)
    public void Start_BeforeSettingViewCausesIllegalStateExceptionError() {
        PlayerAnalysisPersistanceInteracter playerAnalysisPersistanceInteracter = mock(PlayerAnalysisPersistanceInteracter.class);
        PlayerAnalysisBasePresenter playerAnalysisPresenter = new PlayerAnalysisBasePresenter(playerAnalysisPersistanceInteracter);
        playerAnalysisPresenter.start();
    }

    @Test
    public void OnStatCollectionLoadedImpl_TriggersAdapterToBeSet() {
        PlayerAnalysisPersistanceInteracter playerAnalysisPersistanceInteracter = mock(PlayerAnalysisPersistanceInteracter.class);
        PlayerAnalysisBasePresenter playerAnalysisPresenter = new PlayerAnalysisBasePresenter(playerAnalysisPersistanceInteracter);
        PlayerAnalysisView view = mock(PlayerAnalysisView.class);
        playerAnalysisPresenter.setView(view);
        StatSummary statSummary = new StatSummary();
        statSummary.setId(1);
        statSummary.setGoalvalue(12.32);
        statSummary.setStatName("TEST_STAT");
        statSummary.setHasGoal(true);
        StatCollection statCollection = new StatCollection();
        ArrayList<StatPoint> statSummaries = new ArrayList<>();
        statSummaries.add(new StatPoint());
        statCollection.setCollection(statSummaries);

        playerAnalysisPresenter.onStatCollectionLoaded(statCollection);
        verify(view, times(1)).setStatCollectionData(statCollection);
    }

    @Test
    public void OnStatCollectionFailedImpl_TriggersCorrectResponse() {
        PlayerAnalysisPersistanceInteracter playerAnalysisPersistanceInteracter = mock(PlayerAnalysisPersistanceInteracter.class);
        PlayerAnalysisBasePresenter playerAnalysisPresenter = new PlayerAnalysisBasePresenter(playerAnalysisPersistanceInteracter);
        PlayerAnalysisView view = mock(PlayerAnalysisView.class);
        playerAnalysisPresenter.setView(view);
        playerAnalysisPresenter.onStatCollectionFailed("FAILED");
        verify(view, times(1)).showMessage("FAILED");
    }

}
