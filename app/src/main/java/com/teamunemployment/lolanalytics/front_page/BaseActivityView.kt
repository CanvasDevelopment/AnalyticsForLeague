package com.teamunemployment.lolanalytics.front_page

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

import com.squareup.picasso.Picasso
import com.teamunemployment.lolanalytics.front_page.champ_menu.ChampMenuContract
import com.teamunemployment.lolanalytics.front_page.champ_menu.ChampMenuListAdapter
import com.teamunemployment.lolanalytics.front_page.champ_menu.ChampMenuPresenter
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.Utils.Role
import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.data.model.SummonerRapidAccessObject
import com.teamunemployment.lolanalytics.front_page.tabs.TabContract


import java.util.ArrayList

import kotlinx.android.synthetic.main.base.*
import kotlinx.android.synthetic.main.search_test.*
import org.koin.android.ext.android.inject

/**
 * @author Josiah Kendall.
 *
 * This is the base class of the main application page. Holds the bottom bar and the fragment viewpager.
 */
class BaseActivityView : AppCompatActivity(), BaseActivityContract.View, ChampMenuContract.View, TextWatcher {

    private var tabAdapter: TabAdapter? = null

    private val presenter by inject<BaseActivityPresenter>()
    private val searchPresenter by inject<ChampMenuPresenter>()
    private val searchListAdapter = ChampMenuListAdapter(searchPresenter)
    private val summonerRapidAccessObject by inject<SummonerRapidAccessObject>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base)
        // Set views for our presenter
        presenter.setView(this)
        searchPresenter.setSearchView(this)
        // set listener for our search
        searchInput.addTextChangedListener(this)

        // setUpBottomBar();
        setUpTabs()
        setUpClickHandlers()
    }

    override fun onResume() {
        super.onResume()
    }

    fun setCorrectRoleAndChampView() {
            searchPresenter.setCurrentChamp(summonerRapidAccessObject.champKey)
            navigation.selectedItemId = getCorrectButton()
    }

    fun getCorrectButton() : Int {
        val role = Role()
        when (summonerRapidAccessObject.role) {
            role.TOP -> return R.id.action_top
            role.MID -> return R.id.action_mid
            role.JUNGLE -> return R.id.action_jungle
            role.SUP -> return R.id.action_support
            role.ADC -> return R.id.action_adc
        }
        summonerRapidAccessObject.updateRole(role.TOP)
        throw IllegalStateException("Incorrect role set")
    }

    private fun setUpClickHandlers() {
        champFab.setOnClickListener { searchPresenter.handleSearchFabClick() }
        navigation.setOnNavigationItemSelectedListener {
            presenter.handleChangeInRole(it.itemId)
        }

        clearSearchButton.setOnClickListener {
            searchPresenter.clearSearchText()
            refresh()
        }
    }

    private fun setUpTabs() {
        tabAdapter = TabAdapter(supportFragmentManager)
        viewPager.adapter = tabAdapter
        tabs.setupWithViewPager(viewPager)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun setCorrectTabFragment(tab: Int) {
        summonerRapidAccessObject.updateRole(tab)
//        tabAdapter!!.setRole(tab, viewPager.currentItem)
        refresh()
    }

    override fun showOverlay() {
        overlay!!.visibility = View.VISIBLE
    }

    override fun hideOverlay() {
        overlay!!.visibility = View.GONE
    }

    override fun setChampFabIconAsACross() {
         champFab.setImageResource(R.drawable.ic_cancel_white_24px)
    }

    override fun setChampFabIconAsNone() {
        champFab.setImageResource(R.drawable.filter_with_padding)
    }

    override fun setChampFabIconAsSelectedChamp(champIconUrl: String) {
        Picasso.get().load(champIconUrl).into(champFab)
    }

    override fun showChampList() {
        champSearchList!!.visibility = View.VISIBLE
    }

    override fun hideChampList() {
        champSearchList!!.visibility = View.GONE
    }

    override fun showSearchBar() {
        searchBox!!.visibility = View.VISIBLE
    }

    override fun hideSearchBar() {
        searchBox!!.visibility = View.GONE
    }

    override fun setChampList(champs: ArrayList<Champ>) {
        searchListAdapter.setData(champs)
        val horizontalLayoutManager = LinearLayoutManager(this)
        horizontalLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        champSearchList.layoutManager = horizontalLayoutManager
        champSearchList.adapter = searchListAdapter
    }

    override fun ensureKeyboardIsHidden() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun clearSearchField() {

        if (searchInput!!.text != null) {
            searchInput!!.text.clear()
        }

        searchListAdapter.clearFilter()
    }

    override fun showClearSearchTextButton() {
        clearSearchButton!!.visibility = View.VISIBLE
    }

    override fun hideClearSearchTextButton() {
        clearSearchButton!!.visibility = View.INVISIBLE
    }

    override fun filter(searchText: String) {
        searchListAdapter.filter(searchText)
    }


    /**
     * Required for our text watcher for the search. Do not remove
     * @param charSequence
     * @param i
     * @param i1
     * @param i2
     */
    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

    /**
     * Handle a text input into our search box. Update on each key
     * @param charSequence
     * @param i
     * @param i1
     * @param i2
     */
    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
        searchPresenter.searchForChamp(charSequence.toString())
    }

    override fun afterTextChanged(editable: Editable) {

    }

    /**
     * Triggers an update to the view, as our champ has been updated. This means that we need to
     * refresh and update our data
     */
    override fun refresh() {

        var index = 0
        while (index < tabAdapter!!.count) {
            val fragment = tabAdapter!!.getItem(index) as TabContract.View
            fragment.refresh()
            index += 1
        }
    }
}
