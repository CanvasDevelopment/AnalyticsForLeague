package com.teamunemployment.lolanalytics.front_page.Search

import com.teamunemployment.lolanalytics.data.model.Champ

import java.util.ArrayList

import javax.inject.Inject

/**
 * Created by Josiah Kendall
 *
 * This is the presenter for the search in our main view. It is broken up into its own seperate class
 * to prevent the main presenter growing too large.
 */

class SearchPresenter @Inject
constructor(private val searchInteractor: SearchInteractor) {

    private var searchButtonState = 0
    private lateinit var searchView: SearchContract.View

    /**
     * Set the search view for use to present to.
     * @param searchView The view object that we are presenting to.
     */
    fun setSearchView(searchView: SearchContract.View) {
        this.searchView = searchView
    }

    /**
     * Set the champ that the user clicked on.
     * @param champ The champ a user clicked on.
     */
    fun handleChampClick(champ: Champ?) {
        var champ = champ
        // Champurl is empty if we clicked on the "Clear" icon. Therefore we need to wipe the champ settings.
        if (champ!!.champUrl.isEmpty()) {
            champ = null
        }
        searchInteractor.setCurrentChamp(champ)
        closeSearchView()
    }

    fun handleSearchFabClick() {
        if (searchButtonState == OPEN) {
            closeSearchView()
        } else {
            openSearchView()
        }
    }

    /**
     * Search for a champion
     * @param searchText
     */
    fun searchForChamp(searchText: String?) {
        if (searchText != null && searchText.isNotEmpty()) {
            searchView.showClearSearchTextButton()
        } else {
            searchView.hideClearSearchTextButton()
        }
    }

    private fun openSearchView() {
        searchView.showOverlay()
        searchView.setChampFabIconAsACross()
        searchView.showChampList()
        searchView.showSearchBar()
        searchInteractor.getFavouriteChamps(this)
        searchButtonState = OPEN
    }

    private fun closeSearchView() {
        searchView.hideOverlay()
        val champ = searchInteractor.GetCurrentSetChamp()
        if (champ != null) {
            searchView.setChampFabIconAsSelectedChamp(champ.champUrl)
        } else {
            searchView.setChampFabIconAsNone()
        }
        searchView.showChampList()
        searchView.showSearchBar()
        searchView.hideChampList()
        searchView.hideSearchBar()
        searchView.ensureKeyboardIsHidden()
        searchButtonState = ClOSED
    }

    fun setChampRequestResponse(champs: ArrayList<Champ>) {
        val offSetChamp = Champ()
        champs.add(0, offSetChamp)
        val champ = Champ()
        champ.champUrl = ""
        champs.add(1, champ)
        searchView.setChampList(champs)
    }

    fun clearSearchText() {
        searchView.clearSearchField()
    }

    companion object {
        private val OPEN = 1
        private val ClOSED = 0
    }
}
