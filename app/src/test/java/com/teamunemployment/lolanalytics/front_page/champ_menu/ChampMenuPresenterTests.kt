package com.teamunemployment.lolanalytics.front_page.champ_menu

import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.model.Champ

import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import java.util.*

/**
 * Created by Josiah Kendall
 */

class ChampMenuPresenterTests {

    lateinit var champMenuInteractor: ChampMenuInteractor
    lateinit var champMenuPresenter: ChampMenuPresenter
    lateinit var champMenuView: ChampMenuContract.View
    private val id = 121
    private val games = 21
    val summonerId = 21L
    val random = Random()
    val champ = Champ(random.nextInt().toString(),
            id.toString(),
            id.toString(),
            random.toString(),
            random.nextInt().toString(),
            random.nextInt().toString(),
            games,
            "summonerId")
    @Before
    fun doSetUp() {
        champMenuInteractor = mock(ChampMenuInteractor::class.java)
        champMenuPresenter = ChampMenuPresenter(champMenuInteractor,mock(Network::class.java))
        champMenuView = mock(ChampMenuContract.View::class.java)
        champMenuPresenter.setSearchView(champMenuView)



        `when`<Champ>(champMenuInteractor.getCurrentSetChamp()).thenReturn(champ)
    }

    @Test
    fun EnsureWeCanDisplayOverlayWhenChangeFilteredChampButtonIsClicked() {

        champMenuPresenter.handleSearchFabClick()
        verify<ChampMenuContract.View>(
                champMenuView,
                times(1)).showOverlay()
    }

    @Test
    fun EnsureThatWeHideOverlayWhenCloseIsClicked() {
        `when`<Champ>(champMenuInteractor.getCurrentSetChamp()).thenReturn(champ)
        champMenuPresenter.handleSearchFabClick()
        verify<ChampMenuContract.View>(champMenuView, times(1)).showOverlay()
        champMenuPresenter.handleSearchFabClick()
        verify<ChampMenuContract.View>(champMenuView, times(1)).hideOverlay()
    }

    @Test
    fun EnsureThatWeSetCurrentChampIconWhenWeClose() {

        champMenuPresenter.handleSearchFabClick()
        champMenuPresenter.handleSearchFabClick()
        verify(champMenuView, times(1)).setChampFabIconAsSelectedChamp(champ.key)
    }

    @Test
    fun EnsureWeSetSearchButtonToXButton() {

        champMenuPresenter.handleSearchFabClick()
        verify(champMenuView, times(1)).setChampFabIconAsACross()
    }

    @Test
    fun EnsureThatWeShowTheListOfChampions() {

        champMenuPresenter.handleSearchFabClick()

        verify(champMenuView, times(1)).showChampList()
    }

    @Test
    fun EnsureThatWeCloseTheChampList() {
        champMenuPresenter.handleSearchFabClick()
        champMenuPresenter.handleSearchFabClick()
        verify(champMenuView, times(1)).hideChampList()
    }

    @Test
    fun EnsureThatWeShowTheSearchBar() {
        champMenuPresenter.handleSearchFabClick()
        verify(champMenuView, times(1)).showSearchBar()
    }

    @Test
    fun EnsureThatWeCloseTheSearchBarOnSecondClick() {
        champMenuPresenter.handleSearchFabClick()
        champMenuPresenter.handleSearchFabClick()
        verify(champMenuView, times(1)).hideSearchBar()
    }



    @Test
    fun EnsureThatWeSetFavouriteChampsWhenWeFirstLoad() {
        champMenuPresenter.handleSearchFabClick()
        val champs = ArrayList<Champ>()
        val response =  mock(ChampLoadResponse::class.java)
        verify(champMenuInteractor, times(1)).getChamps(response)
        champMenuPresenter.setChampRequestResponse(champs)
        verify(champMenuView, times(1)).setChampList(champs)
    }

    @Test
    fun EnsureThatWeSetMostApplicableChampsWhenWeSearch() {

    }

    @Test
    fun EnsureThatWeCloseOverlayWhenWeSetAChamp() {
        champMenuPresenter.handleChampClick(champ)
        verify(champMenuView, times(1)).hideOverlay()
    }

    @Test
    fun EnsureThatWeSetCorrectImageWhenWeSelectAChamp() {
        val champUrl = "/images/vi.jpg"
        champMenuPresenter.handleChampClick(champ)
        verify(champMenuView, times(1)).setChampFabIconAsSelectedChamp(champ.key)
    }

    @Test
    fun EnsureThatWeCloseTheKeyboard() {
        champMenuPresenter.handleSearchFabClick()
        champMenuPresenter.handleSearchFabClick()
        verify(champMenuView, times(1)).ensureKeyboardIsHidden()
    }

    @Test
    fun EnsureThatWeClearChampSettingWhenWeSelectClearChampInAdapter() {
        champMenuPresenter.handleChampClick(champ)
        verify(champMenuInteractor, times(1)).setCurrentChamp(champ)
    }

    @Test
    fun EnsureThatWeClearTextWhenWeClickOnTheClearTextButtonForTheSearchBox() {
        champMenuPresenter.clearSearchText()
        verify(champMenuView, times(1)).clearSearchField()
    }

    @Test
    fun EnsureThatWeShow_ClearSearchInputButton_WhenWeAddText() {
        champMenuPresenter.searchForChamp("sfff")
        verify(champMenuView, times(1)).showClearSearchTextButton()
    }

    @Test
    fun EnsureThatWeHide_ClearSearchInputButton_WhenWeHaveNoTextLeft() {
        champMenuPresenter.searchForChamp("")
        verify(champMenuView, times(1)).hideClearSearchTextButton()
    }

    @Test
    fun `Ensure that we trigger champ update when we change champ`() {

        champMenuPresenter.handleChampClick(champ)
        verify(champMenuView, times(1)).refresh()
    }
}

