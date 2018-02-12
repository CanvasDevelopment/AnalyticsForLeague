package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab;

import com.teamunemployment.lolanalytics.data.Statics;
import com.teamunemployment.lolanalytics.data.model.MatchIdWrapper;
import com.teamunemployment.lolanalytics.data.model.SummonerRapidAccessObject;
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.MatchHistoryCardViewContract;
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Model.MatchHistoryCardData;

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
        SummonerRapidAccessObject summonerRapidAccessObject = mock(SummonerRapidAccessObject.class);
        MatchHistoryPresenter presenter = new MatchHistoryPresenter(matchHistoryInteractor, summonerRapidAccessObject);

        presenter.setView(mock(MatchHistoryTabView.class));
        presenter.loadDataForRole(Statics.TOP, -1);
        verify(matchHistoryInteractor, times(1)).loadFreshMatchHistoryData(Statics.TOP, -1, presenter);
    }

    @Test(expected = IllegalStateException.class)
    public void LoadMatchList_ThrowsExceptionIfWeHaveNotSetTheViewToPresentTo() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        SummonerRapidAccessObject summonerRapidAccessObject = mock(SummonerRapidAccessObject.class);
        MatchHistoryPresenter presenter = new MatchHistoryPresenter(matchHistoryInteractor, summonerRapidAccessObject);
        presenter.loadDataForRole(Statics.TOP, -1);
        verify(matchHistoryInteractor, times(1)).loadFreshMatchHistoryData(Statics.TOP, -1, presenter);
    }

    @Test(expected = IllegalStateException.class)
    public void LoadMatchSummary_ThrowsExceptionIfWeHaveNotSetTheViewToPresentTo() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        SummonerRapidAccessObject summonerRapidAccessObject = mock(SummonerRapidAccessObject.class);
        MatchHistoryPresenter presenter = new MatchHistoryPresenter(matchHistoryInteractor, summonerRapidAccessObject);
        presenter.loadMatchSummary(-1);
    }

    @Test
    public void LoadMatchSummary_LoadsMatchSummaryFromNetwork() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        SummonerRapidAccessObject summonerRapidAccessObject = mock(SummonerRapidAccessObject.class);
        MatchHistoryPresenter presenter = new MatchHistoryPresenter(matchHistoryInteractor, summonerRapidAccessObject);
        presenter.setView(mock(MatchHistoryTabView.class));
        presenter.loadMatchSummary(123456);
//        verify(matchHistoryInteractor, times(1)).LoadFreshMatchSummary(123456, presenter);
    }

    @Test
    public void LoadMatchList_AttemptsToLoadMatchListFromTheCache() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        SummonerRapidAccessObject summonerRapidAccessObject = mock(SummonerRapidAccessObject.class);
        MatchHistoryPresenter presenter = new MatchHistoryPresenter(matchHistoryInteractor, summonerRapidAccessObject);
        presenter.setView(mock(MatchHistoryTabView.class));
        presenter.loadDataForRole(Statics.TOP, -1);
        verify(matchHistoryInteractor, times(1)).loadCachedMatchHistoryData(Statics.TOP, -1, presenter);
    }

    @Test
    public void LoadMatchSummary_AttemptsToLoadMatchFromCache() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        SummonerRapidAccessObject summonerRapidAccessObject = mock(SummonerRapidAccessObject.class);
        MatchHistoryPresenter presenter = new MatchHistoryPresenter(matchHistoryInteractor, summonerRapidAccessObject);
        presenter.setView(mock(MatchHistoryTabView.class));
        presenter.loadMatchSummary(123456);
//        verify(matchHistoryInteractor, times(1)).loadCachedMatchHistoryData(123456, presenter);
    }

    @Test
    public void LoadMatchList_DoesntHideLoadingSpinnerIfWeDontFindAnythingInTheCache() {

    }

    @Test
    public void OnMatchesLoaded_TriggersSetAdapterWhenLoadedSuccessfully() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        SummonerRapidAccessObject summonerRapidAccessObject = mock(SummonerRapidAccessObject.class);
        MatchHistoryPresenter presenter = new MatchHistoryPresenter(matchHistoryInteractor, summonerRapidAccessObject);
        MatchHistoryTabView tabView = mock(MatchHistoryTabView.class);
        presenter.setView(tabView);
        ArrayList<String> matchIdWrappers = new ArrayList<>();
        presenter.onMatchListLoadedSuccessfully(matchIdWrappers);
        verify(tabView, times(1)).setAdapter(any(MatchHistoryAdapter.class));
    }

    @Test
    public void OnMatchesLoadError_TriggersErrorMessage() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        SummonerRapidAccessObject summonerRapidAccessObject = mock(SummonerRapidAccessObject.class);
        MatchHistoryPresenter presenter = new MatchHistoryPresenter(matchHistoryInteractor, summonerRapidAccessObject);
        MatchHistoryTabView tabView = mock(MatchHistoryTabView.class);
        presenter.setView(tabView);

        presenter.onError(mock(Throwable.class));
        verify(tabView, times(1)).showMessage("An error occurred. Please try again.");
    }

    @Test
    public void LoadCardData_SetsGraph1WithCorrectData() {
        int kills = 0;
        int deaths = 0;
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        SummonerRapidAccessObject summonerRapidAccessObject = mock(SummonerRapidAccessObject.class);
        MatchHistoryPresenter presenter = new MatchHistoryPresenter(matchHistoryInteractor, summonerRapidAccessObject);
        MatchHistoryCardViewContract cardViewContract = mock(MatchHistoryCardViewContract.class);
//        MatchHistoryCardData matchHistoryCardData = new MatchHistoryCardData(1, "vi", kills, deaths, cs1, cs2,cs3,1 );
//        presenter.setLoadedCardData(matchHistoryCardData, cardViewContract);
//        verify(cardViewContract, times(1)).setGraph1(kills);
    }

    @Test
    public void LoadCardData_SetsGraph2WithDeaths() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        SummonerRapidAccessObject summonerRapidAccessObject = mock(SummonerRapidAccessObject.class);
        MatchHistoryPresenter presenter = new MatchHistoryPresenter(matchHistoryInteractor, summonerRapidAccessObject);
        MatchHistoryCardViewContract cardViewContract = mock(MatchHistoryCardViewContract.class);

//        MatchHistoryCardData matchHistoryCardData = new MatchHistoryCardData(1, "vi", kills, deaths, cs1, cs2,cs3, 1);
//        presenter.setLoadedCardData(matchHistoryCardData, cardViewContract);
//        verify(cardViewContract, times(1)).setGraph2(cs3);
    }

    @Test
    public void LoadCardData_SetsGraph3WithCreepScoreFirst10Minutes() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        SummonerRapidAccessObject summonerRapidAccessObject = mock(SummonerRapidAccessObject.class);
        MatchHistoryPresenter presenter = new MatchHistoryPresenter(matchHistoryInteractor, summonerRapidAccessObject);
        MatchHistoryCardViewContract cardViewContract = mock(MatchHistoryCardViewContract.class);

//        MatchHistoryCardData matchHistoryCardData = new MatchHistoryCardData(1, "vi", kills, deaths, cs1, cs2,cs3, 1);
//        presenter.setLoadedCardData(matchHistoryCardData, cardViewContract);
//        verify(cardViewContract, times(1)).setGraph3(cs1);
    }

    @Test
    public void LoadCardData_SetsCorrectChampIconUrl() {
//        Assert.fail();
    }

    @Test
    public void LoadCardData_SetsCorrectKDAString() {
//        Assert.fail();
    }

}
