package com.teamunemployment.lolanalytics.front_page.Search;

import com.teamunemployment.lolanalytics.data.model.Champ;

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
        champ.setChampName("vi");
        champ.setChampUrl("/images/vi.jpg"); // probably make this a R.drawable.reference
        when(searchInteractor.GetCurrentSetChamp()).thenReturn(champ);
    }

    @Test
    public void EnsureWeCanDisplayOverlayWhenChangeFilteredChampButtonIsClicked() {

        searchPresenter.handleSearchFabClick();
        verify(searchView, times(1)).showOverlay();
    }

    @Test
    public void EnsureThatWeHideOverlayWhenCloseIsClicked() {
        when(searchInteractor.GetCurrentSetChamp()).thenReturn(new Champ());
        searchPresenter.handleSearchFabClick();
        verify(searchView, times(1)).showOverlay();
        searchPresenter.handleSearchFabClick();
        verify(searchView, times(1)).hideOverlay();
    }

    @Test
    public void EnsureThatWeSetCurrentChampIconWhenWeClose() {

        searchPresenter.handleSearchFabClick();
        searchPresenter.handleSearchFabClick();
        verify(searchView, times(1)).setChampFabIconAsSelectedChamp("/images/vi.jpg");
    }

    @Test
    public void EnsureWeSetSearchButtonToXButton() {

        searchPresenter.handleSearchFabClick();
        verify(searchView, times(1)).setChampFabIconAsACross();
    }

    @Test
    public void EnsureThatWeShowTheListOfChampions() {

        searchPresenter.handleSearchFabClick();

        verify(searchView, times(1)).showChampList();
    }

    @Test
    public void EnsureThatWeCloseTheChampList() {
        searchPresenter.handleSearchFabClick();
        searchPresenter.handleSearchFabClick();
        verify(searchView, times(1)).hideChampList();
    }

    @Test
    public void EnsureThatWeShowTheSearchBar() {
        searchPresenter.handleSearchFabClick();
        verify(searchView, times(1)).showSearchBar();
    }

    @Test
    public void EnsureThatWeCloseTheSearchBarOnSecondClick() {
        searchPresenter.handleSearchFabClick();
        searchPresenter.handleSearchFabClick();
        verify(searchView, times(1)).hideSearchBar();
    }

    @Test
    public void EnsureThatWeSetFavouriteChampsWhenWeFirstLoad() {
        searchPresenter.handleSearchFabClick();
        ArrayList<Champ> champs = new ArrayList<>();
        verify(searchInteractor, times(1)).getFavouriteChamps(searchPresenter);
        searchPresenter.setChampRequestResponse(champs);
        verify(searchView, times(1)).setChampList(champs);
    }

    @Test
    public void EnsureThatWeSetMostApplicableChampsWhenWeSearch() {

    }

    @Test
    public void EnsureThatWeCloseOverlayWhenWeSetAChamp() {
        Champ champ = new Champ();
        champ.setChampUrl("test");
        champ.setChampName("me");
        searchPresenter.handleChampClick(champ);
        verify(searchView, times(1)).hideOverlay();
    }

    @Test
    public void EnsureThatWeSetCorrectImageWhenWeSelectAChamp() {
        Champ champ = new Champ();
        String champUrl = "/images/vi.jpg";
        champ.setChampUrl(champUrl);
        champ.setChampName("me");
        searchPresenter.handleChampClick(champ);
        verify(searchView, times(1)).setChampFabIconAsSelectedChamp(champUrl);
    }

    @Test
    public void EnsureThatWeCloseTheKeyboard() {
        searchPresenter.handleSearchFabClick();
        searchPresenter.handleSearchFabClick();
        verify(searchView, times(1)).ensureKeyboardIsHidden();
    }

    @Test
    public void EnsureThatWeClearChampSettingWhenWeSelectClearChampInAdapter() {
        Champ champ = new Champ();
        champ.setChampUrl("");
        searchPresenter.handleChampClick(champ);
        verify(searchInteractor, times(1)).setCurrentChamp(null);
    }

    @Test
    public void EnsureThatWeClearTextWhenWeClickOnTheClearTextButtonForTheSearchBox() {
        searchPresenter.clearSearchText();
        verify(searchView, times(1)).clearSearchField();
    }

    @Test
    public void EnsureThatWeShow_ClearSearchInputButton_WhenWeAddText() {
        searchPresenter.searchForChamp("sfff");
        verify(searchView, times(1)).showClearSearchTextButton();
    }

    @Test
    public void EnsureThatWeHide_ClearSearchInputButton_WhenWeHaveNoTextLeft() {
        searchPresenter.searchForChamp("");
        verify(searchView, times(1)).hideClearSearchTextButton();
    }
}

