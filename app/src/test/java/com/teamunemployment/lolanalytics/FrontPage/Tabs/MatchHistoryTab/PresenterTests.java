package com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab;

import com.teamunemployment.lolanalytics.Data.Statics;
import com.teamunemployment.lolanalytics.Data.model.MatchIdWrapper;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.Model.MatchHistoryCardData;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.Model.CardData;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Josiah Kendall
 */
public class PresenterTests {

    @Test
    public void LoadMatchList_LoadsMatchListFromNetwork() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        MatchHistoryBasePresenter presenter = new MatchHistoryBasePresenter(matchHistoryInteractor);

        presenter.setView(mock(MatchHistoryTabView.class));
        presenter.LoadDataForRole(Statics.TOP, -1);
        verify(matchHistoryInteractor, times(1)).LoadFreshMatchHistoryData(Statics.TOP, -1, presenter);
    }

    @Test(expected = IllegalStateException.class)
    public void LoadMatchList_ThrowsExceptionIfWeHaveNotSetTheViewToPresentTo() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        MatchHistoryBasePresenter presenter = new MatchHistoryBasePresenter(matchHistoryInteractor);
        presenter.LoadDataForRole(Statics.TOP, -1);
        verify(matchHistoryInteractor, times(1)).LoadFreshMatchHistoryData(Statics.TOP, -1, presenter);
    }

    @Test(expected = IllegalStateException.class)
    public void LoadMatchSummary_ThrowsExceptionIfWeHaveNotSetTheViewToPresentTo() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        MatchHistoryBasePresenter presenter = new MatchHistoryBasePresenter(matchHistoryInteractor);
        presenter.LoadMatchSummary(-1);
    }

    @Test
    public void LoadMatchSummary_LoadsMatchSummaryFromNetwork() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        MatchHistoryBasePresenter presenter = new MatchHistoryBasePresenter(matchHistoryInteractor);
        presenter.setView(mock(MatchHistoryTabView.class));
        presenter.LoadMatchSummary(123456);
        verify(matchHistoryInteractor, times(1)).LoadFreshMatchSummary(123456, presenter);
    }

    @Test
    public void LoadMatchList_AttemptsToLoadMatchListFromTheCache() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        MatchHistoryBasePresenter presenter = new MatchHistoryBasePresenter(matchHistoryInteractor);
        presenter.setView(mock(MatchHistoryTabView.class));
        presenter.LoadDataForRole(Statics.TOP, -1);
        verify(matchHistoryInteractor, times(1)).LoadCachedMatchHistoryData(Statics.TOP, -1, presenter);
    }

    @Test
    public void LoadMatchSummary_AttemptsToLoadMatchFromCache() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        MatchHistoryBasePresenter presenter = new MatchHistoryBasePresenter(matchHistoryInteractor);
        presenter.setView(mock(MatchHistoryTabView.class));
        presenter.LoadMatchSummary(123456);
        verify(matchHistoryInteractor, times(1)).LoadCachedMatchSummary(123456, presenter);
    }

    @Test
    public void LoadMatchList_DoesntHideLoadingSpinnerIfWeDontFindAnythingInTheCache() {

    }

    @Test
    public void OnMatchesLoaded_TriggersSetAdapterWhenLoadedSuccessfully() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        MatchHistoryBasePresenter presenter = new MatchHistoryBasePresenter(matchHistoryInteractor);
        MatchHistoryTabView tabView = mock(MatchHistoryTabView.class);
        presenter.setView(tabView);
        ArrayList<MatchIdWrapper> matchIdWrappers = new ArrayList<>();
        presenter.onMatchListLoadedSuccessfully(matchIdWrappers);
        verify(tabView, times(1)).setAdapter(any(MatchHistoryAdapter.class));
    }

    @Test
    public void OnMatchesLoadError_TriggersErrorMessage() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        MatchHistoryBasePresenter presenter = new MatchHistoryBasePresenter(matchHistoryInteractor);
        MatchHistoryTabView tabView = mock(MatchHistoryTabView.class);
        presenter.setView(tabView);

        presenter.onError(new Throwable());
        verify(tabView, times(1)).showMessage("An error occurred. Please try again.");
    }

    @Test
    public void LoadCardData_SetsGraph1WithCorrectData() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        MatchHistoryBasePresenter presenter = new MatchHistoryBasePresenter(matchHistoryInteractor);
        MatchHistoryCardViewContract cardViewContract = mock(MatchHistoryCardViewContract.class);
        CardData kills = new CardData(2.3,2.4);
        CardData deaths = new CardData(2.3, 2.4);
        CardData cs1 = new CardData(5.6, 7.6);
        CardData cs2 = new CardData(5.8, 7.2);
        CardData cs3 = new CardData(58, 72);

        MatchHistoryCardData matchHistoryCardData = new MatchHistoryCardData(1, "Vi", kills, deaths, cs1, cs2,cs3);
        presenter.SetLoadedCardData(matchHistoryCardData, cardViewContract);
        verify(cardViewContract, times(1)).setGraph1(kills);
    }

    @Test
    public void LoadCardData_SetsGraph2WithDeaths() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        MatchHistoryBasePresenter presenter = new MatchHistoryBasePresenter(matchHistoryInteractor);
        MatchHistoryCardViewContract cardViewContract = mock(MatchHistoryCardViewContract.class);
        CardData kills = new CardData(2.3,2.4);
        CardData deaths = new CardData(2.3, 2.4);
        CardData cs1 = new CardData(5.6, 7.6);
        CardData cs2 = new CardData(5.8, 7.2);
        CardData cs3 = new CardData(58, 72);

        MatchHistoryCardData matchHistoryCardData = new MatchHistoryCardData(1, "Vi", kills, deaths, cs1, cs2,cs3);
        presenter.SetLoadedCardData(matchHistoryCardData, cardViewContract);
        verify(cardViewContract, times(1)).setGraph2(deaths);
    }

    @Test
    public void LoadCardData_SetsGraph3WithCreepScoreFirst10Minutes() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        MatchHistoryBasePresenter presenter = new MatchHistoryBasePresenter(matchHistoryInteractor);
        MatchHistoryCardViewContract cardViewContract = mock(MatchHistoryCardViewContract.class);
        CardData kills = new CardData(2.3,2.4);
        CardData deaths = new CardData(2.3, 2.4);
        CardData cs1 = new CardData(5.6, 7.6);
        CardData cs2 = new CardData(5.8, 7.2);
        CardData cs3 = new CardData(58, 72);

        MatchHistoryCardData matchHistoryCardData = new MatchHistoryCardData(1, "Vi", kills, deaths, cs1, cs2,cs3);
        presenter.SetLoadedCardData(matchHistoryCardData, cardViewContract);
        verify(cardViewContract, times(1)).setGraph3(cs1);
    }

    @Test
    public void LoadCardData_SetsCorrectChampIconUrl() {
        Assert.fail();
    }

    @Test
    public void LoadCardData_SetsCorrectKDAString() {
        Assert.fail();
    }

}
