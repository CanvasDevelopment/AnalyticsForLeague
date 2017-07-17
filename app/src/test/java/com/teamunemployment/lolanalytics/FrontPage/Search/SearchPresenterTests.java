package com.teamunemployment.lolanalytics.FrontPage.Search;

import com.teamunemployment.lolanalytics.Data.model.Champ;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Josiah Kendall
 */

public class SearchPresenterTests {

    SearchInteractor searchInteractor;
    SearchPresenter searchPresenter;
    SearchContract.View searchView;

    @Before
    public void doSetUp() {
        searchInteractor = mock(SearchInteractor.class);
        searchPresenter = new SearchPresenter(searchInteractor);
        searchView = mock(SearchContract.View.class);
        searchPresenter.setSearchView(searchView);

        Champ champ = new Champ();
        champ.setChampName("Vi");
        champ.setChampUrl("/images/vi.jpg"); // probably make this a R.drawable.reference
        when(searchInteractor.GetCurrentSetChamp()).thenReturn(champ);
    }

    @Test
    public void EnsureWeCanDisplayOverlayWhenChangeFilteredChampButtonIsClicked() {

        searchPresenter.handleSearchClick();
        verify(searchView, times(1)).ShowOverlay();
    }

    @Test
    public void EnsureThatWeHideOverlayWhenCloseIsClicked() {
        when(searchInteractor.GetCurrentSetChamp()).thenReturn(new Champ());
        searchPresenter.handleSearchClick();
        verify(searchView, times(1)).ShowOverlay();
        searchPresenter.handleSearchClick();
        verify(searchView, times(1)).HideOverlay();
    }

    @Test
    public void EnsureThatWeSetCurrentChampIconWhenWeClose() {

        searchPresenter.handleSearchClick();
        searchPresenter.handleSearchClick();
        verify(searchView, times(1)).SetChampFabIconAsSelectedChamp("/images/vi.jpg");
    }

    @Test
    public void EnsureWeSetSearchButtonToXButton() {

        searchPresenter.handleSearchClick();
        verify(searchView, times(1)).SetChampFabIconAsACross();
    }

    @Test
    public void EnsureThatWeShowTheListOfChampions() {

        searchPresenter.handleSearchClick();

        verify(searchView, times(1)).ShowChampList();
    }

    @Test
    public void EnsureThatWeCloseTheChampList() {
        searchPresenter.handleSearchClick();
        searchPresenter.handleSearchClick();
        verify(searchView, times(1)).HideChampList();
    }

    @Test
    public void EnsureThatWeShowTheSearchBar() {

        searchPresenter.handleSearchClick();

        verify(searchView, times(1)).ShowSearchBar();
    }

    @Test
    public void EnsureThatWeCloseTheSearchBarOnSecondClick() {
        searchPresenter.handleSearchClick();
        searchPresenter.handleSearchClick();
        verify(searchView, times(1)).HideSearchBar();
    }

    @Test
    public void EnsureThatWeSetFavouriteChampsWhenWeFirstLoad() {
        searchPresenter.handleSearchClick();
        ArrayList<Champ> champs = new ArrayList<>();
        verify(searchInteractor, times(1)).GetFavouriteChamps();
        searchPresenter.SetChampRequestResponse(champs);
        verify(searchView, times(1)).SetChampList(champs);
    }

    @Test
    public void EnsureThatWeSetMostApplicableChampsWhenWeSearch() {

    }
}

