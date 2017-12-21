package com.teamunemployment.lolanalytics.front_page

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

import com.mikhaellopez.circularimageview.CircularImageView
import com.squareup.picasso.Picasso
import com.teamunemployment.lolanalytics.App
import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.front_page.Search.SearchContract
import com.teamunemployment.lolanalytics.front_page.Search.SearchListAdapter
import com.teamunemployment.lolanalytics.front_page.Search.SearchPresenter
import com.teamunemployment.lolanalytics.R


import java.util.ArrayList

import javax.inject.Inject

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

/**
 * @author Josiah Kendall.
 *
 * This is the base class of the main application page. Holds the bottom bar and the fragment viewpager.
 */
class BaseActivityView : AppCompatActivity(), BaseActivityContract.View, SearchContract.View, TextWatcher {

    private var tabAdapter: TabAdapter? = null

    @Inject
    var presenter: BaseActivityPresenter? = null

    @Inject
    var searchPresenter: SearchPresenter? = null

    @BindView(R.id.container) internal var viewPager: ViewPager? = null
    @BindView(R.id.tabs) internal var tabLayout: TabLayout? = null
    @BindView(R.id.champ_fab) internal var champFab: CircularImageView? = null
    @BindView(R.id.champ_search_list) internal var champList: RecyclerView? = null
    @BindView(R.id.search_card) internal var searchBox: CardView? = null
    @BindView(R.id.search_input) internal var searchInput: EditText? = null
    @BindView(R.id.overlay) internal var overlay: View? = null
    @BindView(R.id.clear_search) internal var clearSearchButton: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base)

        //        ((App) getApplication()).getNetComponent().InjectView(this);

        ButterKnife.bind(this)

        // Set views for our presenter
        presenter!!.setView(this)
        searchPresenter!!.setSearchView(this)

        // set listener for our search
        searchInput!!.addTextChangedListener(this)

        // setUpBottomBar();
        setUpTabs()
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
        viewPager!!.adapter = tabAdapter
        tabLayout!!.setupWithViewPager(viewPager)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun setCorrectTabFragment(tab: Int) {

        // Rather than set correct tab, this shold probably be set correct role.
        // currentTab.setRole(role)
        tabAdapter!!.setRole(tab)
    }

    override fun ShowOverlay() {
        overlay!!.visibility = View.VISIBLE
    }

    override fun HideOverlay() {
        overlay!!.visibility = View.GONE
    }

    override fun SetChampFabIconAsACross() {
        champFab!!.setImageResource(R.drawable.ic_cancel_white_24px)
    }

    override fun SetChampFabIconAsNone() {
        champFab!!.setImageResource(R.drawable.ic_account_circle_white_24px)
    }

    override fun SetChampFabIconAsSelectedChamp(champIconUrl: String) {
        Picasso.with(this).load(R.drawable.khazix).into(champFab)
    }

    override fun ShowChampList() {
        champList!!.visibility = View.VISIBLE
    }

    override fun HideChampList() {
        champList!!.visibility = View.GONE
    }

    override fun ShowSearchBar() {
        searchBox!!.visibility = View.VISIBLE
    }

    override fun HideSearchBar() {
        searchBox!!.visibility = View.GONE
    }

    override fun SetChampList(champs: ArrayList<Champ>) {
        val searchListAdapter = SearchListAdapter(searchPresenter)
        searchListAdapter.SetData(champs)
        val horizontalLayoutManager = LinearLayoutManager(this)
        horizontalLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        champList!!.layoutManager = horizontalLayoutManager
        champList!!.adapter = searchListAdapter
    }

    override fun ensureKeyboardIsHidden() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun ClearSearchText() {

        if (searchInput!!.text != null) {
            searchInput!!.text.clear()
        }
    }

    override fun ShowClearSearchTextButton() {
        clearSearchButton!!.visibility = View.VISIBLE
    }

    override fun HideClearSearchTextButton() {
        clearSearchButton!!.visibility = View.INVISIBLE
    }

    @OnClick(R.id.champ_fab) internal fun handleChampFilterClick() {
        searchPresenter!!.handleSearchFabClick()
    }

    @OnClick(R.id.overlay) internal fun consumeOverlayClicks() {
        // Probably just hide when this happens
        searchPresenter!!.handleSearchFabClick()
    }

    @OnClick(R.id.clear_search) internal fun clearSearchText() {
        searchPresenter!!.ClearSearchText()
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
        searchPresenter!!.searchForChamp(charSequence.toString())
    }

    override fun afterTextChanged(editable: Editable) {

    }
}
