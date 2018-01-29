package com.teamunemployment.lolanalytics.front_page.Search;

import com.teamunemployment.lolanalytics.data.model.Champ;

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
            searchView.showClearSearchTextButton();
        } else {
            searchView.hideClearSearchTextButton();
        }
    }

    private void openSearchView() {
        searchView.showOverlay();
        searchView.setChampFabIconAsACross();
        searchView.showChampList();
        searchView.showSearchBar();
        searchInteractor.GetFavouriteChamps(this);
        searchButtonState = OPEN;
    }

    private void closeSearchView() {
        searchView.hideOverlay();
        Champ champ = searchInteractor.GetCurrentSetChamp();
        if (champ != null) {
            searchView.setChampFabIconAsSelectedChamp(champ.getChampUrl());
        } else {
            searchView.setChampFabIconAsNone();
        }
        searchView.showChampList();
        searchView.showSearchBar();
        searchView.hideChampList();
        searchView.hideSearchBar();
        searchView.ensureKeyboardIsHidden();
        searchButtonState = ClOSED;
    }

    @Override
    public void SetChampRequestResponse(ArrayList<Champ> champs) {
        Champ offSetChamp = new Champ();
        champs.add(0, offSetChamp);
        Champ champ = new Champ();
        champ.setChampUrl("");
        champs.add(1, champ);
        searchView.setChampList(champs);
    }

    public void ClearSearchText() {
        searchView.clearSearchField();
    }
}
