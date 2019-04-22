package com.teamunemployment.lolanalytics.front_page.champ_menu

import com.teamunemployment.lolanalytics.BuildConfig
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.model.Champ
import timber.log.Timber

import java.util.ArrayList

/**
 * Created by Josiah Kendall
 *
 * This is the presenter for the search in our main view. It is broken up into its own seperate class
 * to prevent the main presenter growing too large.
 */

class ChampMenuPresenter
constructor(private val champMenuInteractor : ChampMenuInteractor,
            private val network : Network) : ChampLoadResponse {

    private var searchButtonState = 0
    private lateinit var champMenuView: ChampMenuContract.View

    /**
     * Set the search view for use to present to.
     * @param champMenuView The view object that we are presenting to.
     */
    fun setSearchView(champMenuView: ChampMenuContract.View) {
        this.champMenuView = champMenuView
    }

    /**
     * Set the champ that the user clicked on.
     * @param champ The champ a user clicked on.
     */
    fun handleChampClick(champ: Champ?) {
        // Champurl is empty if we clicked on the "Clear" icon. Therefore we need to wipe the champ settings.
        champMenuInteractor.setCurrentChamp(champ)
        closeSearchView()
        champMenuView.refresh()
    }

    fun setCurrentChamp(champKey: Int) {
        champMenuInteractor.setCurrentChamp(champKey,  {
            closeSearchView()
            champMenuView.refresh()
        })

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
            champMenuView.showClearSearchTextButton()
            champMenuView.filter(searchText)
        } else {
            champMenuView.hideClearSearchTextButton()
            champMenuView.clearSearchField()
        }


    }

    private fun openSearchView() {
        champMenuView.showOverlay()
        champMenuView.setChampFabIconAsACross()
        champMenuView.showChampList()
        champMenuView.showSearchBar()
        // fetch our champs
        champMenuInteractor.getChamps(this)
        searchButtonState = OPEN
    }

    private fun closeSearchView() {
        champMenuView.hideOverlay()
        val champ = champMenuInteractor.getCurrentSetChamp()
        if (champ != null) {
            val champString = champ.title
                .replace(" ", "")
                .replace("'", "")
                .replace(".", "")
            val url = if (BuildConfig.DEBUG) {
                "${network.getRawUrl("")}/champion/$champString.png"
            } else {
                ""
            }
            champMenuView.setChampFabIconAsSelectedChamp(url)
        } else {
            champMenuView.setChampFabIconAsNone()
        }
        champMenuView.showChampList()
        champMenuView.showSearchBar()
        champMenuView.hideChampList()
        champMenuView.hideSearchBar()
        champMenuView.ensureKeyboardIsHidden()
        searchButtonState = ClOSED
    }

    fun setChampRequestResponse(champs : ArrayList<Champ>) {
//        val offSetChamp = Champ("","","","","","",0,0L)
//        champs.add(0, offSetChamp)
//        val champ = Champ("","","","","","",0,0L)
//        champs.add(1, champ)
        champMenuView.setChampList(champs)
    }

    fun clearSearchText() {
        champMenuView.clearSearchField()
    }

    companion object {
        private val OPEN = 1
        private val ClOSED = 0
    }

    override fun failure() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun success(champs: List<Champ>) {
        val champArray = ArrayList(champs)
        val offSetChamp = Champ("","","","","","",0,"0L")
        champArray.add(0, offSetChamp)
        val offSetChamp1 = Champ("","","","","","",0,"0L")
        champArray.add(1, offSetChamp1)

        champMenuView.setChampList(champArray)
    }

    fun getUrl(champString: String): String {
        return "${network.getRawUrl("")}/champion/$champString.png"
    }

    fun clearChamp() {
        champMenuView.setChampFabIconAsNone()
        champMenuInteractor.clearCurrentChamp()
    }
}
