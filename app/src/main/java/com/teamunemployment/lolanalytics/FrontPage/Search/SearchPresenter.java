package com.teamunemployment.lolanalytics.FrontPage.Search;

import com.teamunemployment.lolanalytics.Data.model.Champ;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Josiah Kendall
 *
 * This is the presenter for the search in our main view. It is broken up into its own seperate class
 * to prevent the main presenter growing too large.
 */

public class SearchPresenter implements SearchContract.Presenter {
    private static final int OPEN = 1;
    private static final int ClOSED = 0;
    private int searchButtonState = 0;
    private SearchContract.View searchView;
    private SearchInteractor searchInteractor;

    @Inject
    public SearchPresenter(SearchInteractor searchInteractor) {
        this.searchInteractor = searchInteractor;
    }

    /**
     * Set the search view for use to present to.
     * @param searchView The view object that we are presenting to.
     */
    public void setSearchView(SearchContract.View searchView) {
        this.searchView = searchView;
    }

    /**
     * Set the champ that the user clicked on.
     * @param champ The champ a user clicked on.
     */
    public void HandleChampClick(Champ champ) {
        // Champurl is empty if we clicked on the "Clear" icon. Therefore we need to wipe the champ settings.
        if (champ.getChampUrl().isEmpty()) {
            champ = null;
        }
        searchInteractor.SetCurrentChamp(champ);
        closeSearchView();
    }

    public void handleSearchFabClick() {
        if(searchButtonState == OPEN) {
            closeSearchView();
        } else {
            openSearchView();
        }
    }

    /**
     * Search for a champion
     * @param searchText
     */
    public void searchForChamp(String searchText) {
        if (searchText != null && searchText.length() > 0) {
            searchView.ShowClearSearchTextButton();
        } else {
            searchView.HideClearSearchTextButton();
        }
    }

    private void openSearchView() {
        searchView.ShowOverlay();
        searchView.SetChampFabIconAsACross();
        searchView.ShowChampList();
        searchView.ShowSearchBar();
        searchInteractor.GetFavouriteChamps(this);
        searchButtonState = OPEN;
    }

    private void closeSearchView() {
        searchView.HideOverlay();
        Champ champ = searchInteractor.GetCurrentSetChamp();
        if (champ != null) {
            searchView.SetChampFabIconAsSelectedChamp(champ.getChampUrl());
        } else {
            searchView.SetChampFabIconAsNone();
        }
        searchView.ShowChampList();
        searchView.ShowSearchBar();
        searchView.HideChampList();
        searchView.HideSearchBar();
        searchView.ensureKeyboardIsHidden();
        searchButtonState = ClOSED;
    }

    @Override
    public void SetChampRequestResponse(ArrayList<Champ> champs) {
        Champ champ = new Champ();
        champ.setChampUrl("");
        champs.add(0, champ);
        searchView.SetChampList(champs);
    }

    public void ClearSearchText() {
        searchView.ClearSearchText();
    }
}
