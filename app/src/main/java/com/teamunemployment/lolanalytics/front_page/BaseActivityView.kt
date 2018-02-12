package com.teamunemployment.lolanalytics.front_page

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast

import com.squareup.picasso.Picasso
import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.front_page.Search.SearchContract
import com.teamunemployment.lolanalytics.front_page.Search.SearchListAdapter
import com.teamunemployment.lolanalytics.front_page.Search.SearchPresenter
import com.teamunemployment.lolanalytics.R


import java.util.ArrayList

import kotlinx.android.synthetic.main.base.*
import kotlinx.android.synthetic.main.search_test.*
import org.koin.android.ext.android.inject

/**
 * @author Josiah Kendall.
 *
 * This is the base class of the main application page. Holds the bottom bar and the fragment viewpager.
 */
class BaseActivityView : AppCompatActivity(), BaseActivityContract.View, SearchContract.View, TextWatcher {

    private var tabAdapter: TabAdapter? = null

    private val presenter by inject<BaseActivityPresenter>()
    private val searchPresenter by inject<SearchPresenter>()

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

    private fun setUpClickHandlers() {
        champFab.setOnClickListener { searchPresenter.handleSearchFabClick() }
    }

    /**
     * Set up colors and buttons and click handlers. Somewhat long method but there is no real logic.
     */
    private fun setUpBottomBar() {

        // Bottom bar appearance.
        //        bottomBar.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        //        bottomBar.setAccentColor(ContextCompat.getColor(this, R.color.colorAccent));
        //        bottomBar.setInactiveColor(ContextCompat.getColor(this, R.color.bluegrey));
        //        bottomBar.setBehaviorTranslationEnabled(true);
        //        presenter.setUpBottomBar(bottomBar);
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

        // Rather than set correct tab, this shold probably be set correct role.
        // currentTab.setRole(role)
        tabAdapter!!.setRole(tab)
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
        champFab.setImageResource(R.drawable.ic_account_circle_white_24px)
    }

    override fun setChampFabIconAsSelectedChamp(champIconUrl: String) {
        Picasso.with(this).load(R.drawable.khazix).into(champFab)
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
        val searchListAdapter = SearchListAdapter(searchPresenter)
        searchListAdapter.SetData(champs)
        val horizontalLayoutManager = LinearLayoutManager(this)
        horizontalLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        champSearchList!!.layoutManager = horizontalLayoutManager
        champSearchList!!.adapter = searchListAdapter
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
    }

    override fun showClearSearchTextButton() {
        clearSearchButton!!.visibility = View.VISIBLE
    }

    override fun hideClearSearchTextButton() {
        clearSearchButton!!.visibility = View.INVISIBLE
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
}
