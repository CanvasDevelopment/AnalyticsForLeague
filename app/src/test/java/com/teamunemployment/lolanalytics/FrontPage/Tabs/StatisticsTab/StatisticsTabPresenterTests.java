package com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab;

import com.teamunemployment.lolanalytics.Data.model.Champ;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab.model.GameStageStatisticModel;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab.model.ThreeGameStageStatistic;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatisticsTab.model.StatisticsGameStageComparisonViewHolder;
import com.teamunemployment.lolanalytics.Utils.Filters.ChampSingleton;
import com.teamunemployment.lolanalytics.Utils.Filters.RoleSingleton;
import com.teamunemployment.lolanalytics.Utils.RoleUtils;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Josiah Kendall
 */

public class StatisticsTabPresenterTests {

    private StatisticsTabPresenter presenter;
    private StatisticsTabAdapter adapter;
    private StatisticsTabInteractor interactor;
    private StatisticsTabContract.View view;
    private RoleSingleton roleSingleton;
    private ChampSingleton champSingleton;
    private RoleUtils roleUtils;

    @Before
    public void setUp() {
        adapter = mock(StatisticsTabAdapter.class);
        view = mock(StatisticsTabContract.View.class);
        interactor = mock(StatisticsTabInteractor.class);
        champSingleton = mock(ChampSingleton.class);
        roleSingleton = mock(RoleSingleton.class);
        roleUtils = mock(RoleUtils.class);
        presenter = new StatisticsTabPresenter(interactor, adapter,champSingleton, roleSingleton, roleUtils);
        presenter.setView(view);
    }

    @Test
    public void ensureThatWeSetAndShowTheAdapterWhenWeHaveData() {
        ArrayList<ThreeGameStageStatistic> dataModels = new ArrayList<>();
        ThreeGameStageStatistic threeGameStageStatistic = new ThreeGameStageStatistic();
        dataModels.add(threeGameStageStatistic);
        presenter.handleDataResponse(dataModels);
        verify(view, times(1)).setAdapter(adapter);
    }

    @Test
    public void EnsureThatWeShowPlaceholderWhenWeHaveNoData() {
        ArrayList<ThreeGameStageStatistic> dataModels = new ArrayList<>();
        presenter.handleDataResponse(dataModels);
        verify(view, times(0)).setAdapter(adapter);
        verify(view, times(1)).setNoDataPlaceholderVisible();
    }

    @Test
    public void EnsureThatWeHideListWhenWeHaveNoData() {
        ArrayList<ThreeGameStageStatistic> dataModels = new ArrayList<>();
        presenter.handleDataResponse(dataModels);
        verify(view, times(1)).setListHidden();
    }

    @Test
    public void EnsureThatWeHidePlaceholderWhenWeHaveData() {
        ArrayList<ThreeGameStageStatistic> dataModels = new ArrayList<>();
        ThreeGameStageStatistic threeGameStageStatistic = new ThreeGameStageStatistic();
        dataModels.add(threeGameStageStatistic);
        presenter.handleDataResponse(dataModels);
        verify(view, times(1)).setNoDataPlaceholderHidden();
    }

    @Test
    public void EnsureThatWeSetTheCorrectPlaceholderMessageForChampAndRole() {
        ArrayList<ThreeGameStageStatistic> dataModels = new ArrayList<>();
        Champ champ = new Champ();
        champ.setChampName("Vi");
        when(champSingleton.getCurrentChamp()).thenReturn(champ);
        when(roleSingleton.getRole()).thenReturn(1);
        when(roleUtils.GetRoleName(1)).thenReturn("JUNGLE");
        presenter.handleDataResponse(dataModels);
        verify(view, times(1)).setPlaceholderString("No games found as JUNGLE with Vi");
        verify(view, times(1)).setListHidden();
    }

    @Test
    public void EnsureThatWeSetTheCorrectPlaceholerMessageWhenWeHaveJustARole() {
        ArrayList<ThreeGameStageStatistic> dataModels = new ArrayList<>();
        when(champSingleton.getCurrentChamp()).thenReturn(null);
        when(roleSingleton.getRole()).thenReturn(1);
        when(roleUtils.GetRoleName(1)).thenReturn("JUNGLE");
        presenter.handleDataResponse(dataModels);
        verify(view, times(1)).setPlaceholderString("No games found as JUNGLE");
        verify(view, times(1)).setListHidden();
    }

    @Test
    public void EnsureThatWeGetDataForChampAndRoleIfWeHaveAChampSet() {
        Champ champ = new Champ();
        champ.setChampName("Vi");
        champ.setChampId(1);
        when(champSingleton.getCurrentChamp()).thenReturn(champ);
        when(roleSingleton.getRole()).thenReturn(1);

        // Start and verify
        presenter.start();
        verify(interactor, times(1)).getStatistics(1,1, presenter);
    }

    @Test
    public void EnsureThatWeGetDataForJustRoleIfWeHAveNoChampSet() {
        when(champSingleton.getCurrentChamp()).thenReturn(null);
        when(roleSingleton.getRole()).thenReturn(1);

        // Start and verify
        presenter.start();
        verify(interactor, times(1)).getStatistics(1, presenter);
    }

    @Test
    public void EnsureThatWeSetStatTitleCorrectly() {
        String title = "Creep Score vs Opponent";
        StatisticsGameStageComparisonViewHolder holder = mock(StatisticsGameStageComparisonViewHolder.class);
        ArrayList<ThreeGameStageStatistic> dataModels = new ArrayList<>();
        ThreeGameStageStatistic threeGameStageStatistic = new ThreeGameStageStatistic();
        threeGameStageStatistic.setTitle(title);
        dataModels.add(threeGameStageStatistic);
        presenter.handleDataResponse(dataModels);
        presenter.onCardBinding(holder, 0);
        verify(holder, times(1)).setTitle(title);
    }

    @Test
    public void EnsureThatWeSetEarlyGameStatsCorrectly() {
        String title = "Creep Score vs Opponent";
        StatisticsGameStageComparisonViewHolder holder = mock(StatisticsGameStageComparisonViewHolder.class);
        ArrayList<ThreeGameStageStatistic> dataModels = new ArrayList<>();
        ThreeGameStageStatistic threeGameStageStatistic = new ThreeGameStageStatistic();
        threeGameStageStatistic.setTitle(title);
        GameStageStatisticModel earlyGame = mock(GameStageStatisticModel.class);
        threeGameStageStatistic.setEarlyGame(earlyGame);
        dataModels.add(threeGameStageStatistic);
        presenter.handleDataResponse(dataModels);
        presenter.onCardBinding(holder, 0);
        verify(holder, times(1)).setEarlyGame(earlyGame);
    }

    @Test
    public void EnsureThatWeSetMidGameStatsCorrectly() {
        String title = "Creep Score vs Opponent";
        StatisticsGameStageComparisonViewHolder holder = mock(StatisticsGameStageComparisonViewHolder.class);
        ArrayList<ThreeGameStageStatistic> dataModels = new ArrayList<>();
        ThreeGameStageStatistic threeGameStageStatistic = new ThreeGameStageStatistic();
        threeGameStageStatistic.setTitle(title);
        GameStageStatisticModel midGame = mock(GameStageStatisticModel.class);
        threeGameStageStatistic.setMidGame(midGame);
        dataModels.add(threeGameStageStatistic);
        presenter.handleDataResponse(dataModels);
        presenter.onCardBinding(holder, 0);
        verify(holder, times(1)).setMidGame(midGame);
    }

    @Test
    public void EnsureThatWeSetLateGameStatsCorrectly() {
        String title = "Creep Score vs Opponent";
        StatisticsGameStageComparisonViewHolder holder = mock(StatisticsGameStageComparisonViewHolder.class);
        ArrayList<ThreeGameStageStatistic> dataModels = new ArrayList<>();
        ThreeGameStageStatistic threeGameStageStatistic = new ThreeGameStageStatistic();
        threeGameStageStatistic.setTitle(title);
        GameStageStatisticModel lateGame = mock(GameStageStatisticModel.class);
        threeGameStageStatistic.setLateGame(lateGame);
        dataModels.add(threeGameStageStatistic);
        presenter.handleDataResponse(dataModels);
        presenter.onCardBinding(holder, 0);
        verify(holder, times(1)).setLateGame(lateGame);
    }

    // TODO decide if these pie things need to be in their own class.
    @Test
    public void EnsureThatWeHidePieChartTitle() {

    }

    @Test
    public void makeSureThatTheFirstItemInTheListIsTheSearchbar() {

    }

    @Test
    public void makeSureThatASearchTriggersTheRequestOfNewData() {

    }

    
}
