package com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab;

import com.teamunemployment.lolanalytics.Utils.Role;
import com.teamunemployment.lolanalytics.data.Statics;
import com.teamunemployment.lolanalytics.data.model.SummonerRapidAccessObject;
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.MatchHistoryCardViewContract;
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.MatchIdentifier;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Josiah Kendall
 */
public class PresenterTests {

    Role role = new Role();
    MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
    SummonerRapidAccessObject summonerRapidAccessObject = mock(SummonerRapidAccessObject.class);
    MatchHistoryRecycleViewAdapter matchHistoryRecycleViewAdapter = new MatchHistoryRecycleViewAdapter();
    MatchHistoryPresenter presenter = new MatchHistoryPresenter(matchHistoryInteractor, summonerRapidAccessObject, role, matchHistoryRecycleViewAdapter);
    MatchHistoryCardViewContract matchHistoryCardViewContract = mock(MatchHistoryCardViewContract.class);
    String summonerId = "Josiahs_ID";
    String region = "OCE";
    int roleString = 1;

    @Before
    public void setUp() {

        when(summonerRapidAccessObject.getSummonerId()).thenReturn(summonerId);
        when(summonerRapidAccessObject.getRegion()).thenReturn(region);
        when(summonerRapidAccessObject.getRole()).thenReturn(roleString);

    }
    // vnot going to work atm
    @Test
    public void LoadMatchList_LoadsMatchListFromNetwork() {

        presenter.setView(mock(MatchHistoryTabView.class));
        presenter.loadDataForRole(Statics.TOP, "-1");
        verify(matchHistoryInteractor, times(1)).loadMatchDetails(1234L,
                role.getTOP(),
                presenter,
                matchHistoryCardViewContract,
                "OCE",
                "-1",
                -1L);
    }

    @Test(expected = IllegalStateException.class)
    public void LoadMatchList_ThrowsExceptionIfWeHaveNotSetTheViewToPresentTo() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        presenter.loadDataForRole(Statics.TOP, "-1");
        verify(matchHistoryInteractor, times(1)).loadMatchDetails(1234L,
                role.getTOP(),
                presenter,
                matchHistoryCardViewContract,
                "OCE",
                "-1",
                -1L);
    }

    @Test(expected = IllegalStateException.class)
    public void LoadMatchSummary_ThrowsExceptionIfWeHaveNotSetTheViewToPresentTo() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        SummonerRapidAccessObject summonerRapidAccessObject = mock(SummonerRapidAccessObject.class);
        presenter.loadMatchSummary(-1);
    }

    @Test
    public void LoadMatchSummary_LoadsMatchSummaryFromNetwork() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        SummonerRapidAccessObject summonerRapidAccessObject = mock(SummonerRapidAccessObject.class);
        presenter.setView(mock(MatchHistoryTabView.class));
        presenter.loadMatchSummary(123456);
//        verify(matchHistoryInteractor, times(1)).LoadFreshMatchSummary(123456, presenter);
    }

    @Test
    public void LoadMatchList_AttemptsToLoadMatchListFromTheCache() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        SummonerRapidAccessObject summonerRapidAccessObject = mock(SummonerRapidAccessObject.class);
        presenter.setView(mock(MatchHistoryTabView.class));
        presenter.loadDataForRole(Statics.TOP, "-1");
        verify(matchHistoryInteractor, times(1)).loadMatchDetails(1234L,
                role.getTOP(),
                presenter,
                matchHistoryCardViewContract,
                "OCE",
                "-1",
                -1L);    }

    @Test
    public void LoadMatchSummary_AttemptsToLoadMatchFromCache() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        SummonerRapidAccessObject summonerRapidAccessObject = mock(SummonerRapidAccessObject.class);
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
        MatchHistoryTabView tabView = mock(MatchHistoryTabView.class);
        presenter.setView(tabView);
        ArrayList<MatchIdentifier> matchIdWrappers = new ArrayList<>();
        presenter.onMatchListLoadedSuccessfully(matchIdWrappers, 200);
        verify(matchHistoryInteractor, times(1)).loadMatchDetails(1234L,
                role.getTOP(),
                presenter,
                matchHistoryCardViewContract,
                "OCE",
                "-1",
                -1L);
    }

    @Test
    public void OnMatchesLoadError_TriggersErrorMessage() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        SummonerRapidAccessObject summonerRapidAccessObject = mock(SummonerRapidAccessObject.class);
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
        MatchHistoryCardViewContract cardViewContract = mock(MatchHistoryCardViewContract.class);
//        MatchHistoryCardData matchHistoryCardData = new MatchHistoryCardData(1, "vi", kills, deaths, cs1, cs2,cs3,1 );
//        presenter.setLoadedCardData(matchHistoryCardData, cardViewContract);
//        verify(cardViewContract, times(1)).setGraph1(kills);
    }

    @Test
    public void LoadCardData_SetsGraph2WithDeaths() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        SummonerRapidAccessObject summonerRapidAccessObject = mock(SummonerRapidAccessObject.class);
        MatchHistoryCardViewContract cardViewContract = mock(MatchHistoryCardViewContract.class);

//        MatchHistoryCardData matchHistoryCardData = new MatchHistoryCardData(1, "vi", kills, deaths, cs1, cs2,cs3, 1);
//        presenter.setLoadedCardData(matchHistoryCardData, cardViewContract);
//        verify(cardViewContract, times(1)).setGraph2(cs3);
    }

    @Test
    public void LoadCardData_SetsGraph3WithCreepScoreFirst10Minutes() {
        MatchHistoryInteractor matchHistoryInteractor = mock(MatchHistoryInteractor.class);
        SummonerRapidAccessObject summonerRapidAccessObject = mock(SummonerRapidAccessObject.class);
        MatchHistoryCardViewContract cardViewContract = mock(MatchHistoryCardViewContract.class);

//        MatchHistoryCardData matchHistoryCardData = new MatchHistoryCardData(1, "vi", kills, deaths, cs1, cs2,cs3, 1);
//        presenter.setLoadedCardData(matchHistoryCardData, cardViewContract);
//        verify(cardViewContract, times(1)).setGraph3(cs1);
    }

    @Test
    public void makeSureThatWeFetchTheCorrectMethodIfWeDoNotHaveAChampSet() {
//        when(summonerRapidAccessObject.getChamp()).thenReturn(-1);
//        presenter.setView(mock(TabContract.View.class));
//        presenter.start();
//
//        verify(matchHistoryInteractor,times(1))
//                .loadMatches(
//                        "oce",
//                        summonerRapidAccessObject.getSummonerId(),
//                        summonerRapidAccessObject.getRole(),
//                        0,
//                        presenter);
    }

    @Test
    public void makeSureThatWeFetchTheCorrectMethodIfWeHaveAChampSet() {
//        when(summonerRapidAccessObject.getChamp()).thenReturn(3);
//        presenter.setView(mock(TabContract.View.class));
//        presenter.start();
//
//        verify(matchHistoryInteractor,times(1))
//                .loadMatches(
//                        "oce",
//                        summonerRapidAccessObject.getSummonerId(),
//                        summonerRapidAccessObject.getRole(),
//                        3,
//                        0,
//                        presenter);

    }

    @Test
    public void makeSureThatWeDoNotLoadNewInformationIfWeDoNotHaveMoreThan20ItemsInTheList() {
        when(summonerRapidAccessObject.getChampKey()).thenReturn(-1);
        presenter.loadMoreDataForRole(20);
        verify(matchHistoryInteractor, times(1)).loadMatches(region,summonerId,roleString,20,presenter);
    }

    @Test
    public void makeSureThatWeDoNotLoadNewInformationIfWeDoNotHaveMoreThan20ItemsInTheListAndWeHaveAChamp() {
        when(summonerRapidAccessObject.getChampKey()).thenReturn(10);
        presenter.loadMoreDataForRole(20);
        verify(matchHistoryInteractor, times(1)).loadMatches(region,summonerId,roleString,10,20,presenter);
    }

    @Test
    public void makeSureThatWeDoNotLoadNewInformationIfWeDoNotHaveMoreThan40ItemsInTheList() {
        when(summonerRapidAccessObject.getChampKey()).thenReturn(10);
        presenter.loadMoreDataForRole(21);
        verify(matchHistoryInteractor, times(0)).loadMatches(region,summonerId,roleString,10,21,presenter);

    }

    @Test
    public void makeSureThatWeDoLoadNewInformationIfWeHaveMoreThan20ItemsInTheList() {

    }

    @Test
    public void makeSureThatWeDoLoadNewInformationIfWeHaveMoreThan40ItemsInTheList() {

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
