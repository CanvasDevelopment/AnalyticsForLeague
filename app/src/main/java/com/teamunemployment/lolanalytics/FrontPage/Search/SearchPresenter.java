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

    public void handleSearchClick() {
        if(searchButtonState == OPEN) {
            closeSearchView();
        } else {
            openSearchView();
        }
    }

    private void openSearchView() {
        searchView.ShowOverlay();
        searchView.SetChampFabIconAsACross();
        searchView.ShowChampList();
        searchView.ShowSearchBar();
        searchInteractor.GetFavouriteChamps();
        searchButtonState = OPEN;
    }

    private void closeSearchView() {
        searchView.HideOverlay();
        Champ champ = searchInteractor.GetCurrentSetChamp();
        searchView.SetChampFabIconAsSelectedChamp(champ.getChampUrl());
        searchView.ShowChampList();
        searchView.ShowSearchBar();
        searchView.HideChampList();
        searchView.HideSearchBar();
        searchInteractor.GetFavouriteChamps();
        searchButtonState = OPEN;
    }

    @Override
    public void SetChampRequestResponse(ArrayList<Champ> champs) {
        searchView.SetChampList(champs);
    }
}
