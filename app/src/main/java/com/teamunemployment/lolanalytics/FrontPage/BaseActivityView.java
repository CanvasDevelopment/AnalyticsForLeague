package com.teamunemployment.lolanalytics.FrontPage;

import android.content.Context;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.teamunemployment.lolanalytics.App;
import com.teamunemployment.lolanalytics.Data.model.Champ;
import com.teamunemployment.lolanalytics.FrontPage.Search.SearchContract;
import com.teamunemployment.lolanalytics.FrontPage.Search.SearchListAdapter;
import com.teamunemployment.lolanalytics.FrontPage.Search.SearchPresenter;
import com.teamunemployment.lolanalytics.R;


import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Josiah Kendall.
 *
 * This is the base class of the main application page. Holds the bottom bar and the fragment viewpager.
 */
public class BaseActivityView extends AppCompatActivity implements BaseActivityContract.View, SearchContract.View, TextWatcher {

    private TabAdapter tabAdapter;

    @Inject
    public BaseActivityPresenter presenter;

    @Inject
    public SearchPresenter searchPresenter;

    @Bind(R.id.container) ViewPager viewPager;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.champ_fab)
    CircularImageView champFab;
    @Bind(R.id.champ_search_list) RecyclerView champList;
    @Bind(R.id.search_card) CardView searchBox;
    @Bind(R.id.search_input) EditText searchInput;
    @Bind(R.id.overlay) View overlay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);

        ((App) getApplication()).getNetComponent().InjectView(this);

        ButterKnife.bind(this);

        // Set views for our presenter
        presenter.setView(this);
        searchPresenter.setSearchView(this);

        // set listener for our search
        searchInput.addTextChangedListener(this);

       // setUpBottomBar();
        setUpTabs();
    }

    /**
     * Set up colors and buttons and click handlers. Somewhat long method but there is no real logic.
     */
    private void setUpBottomBar() {

        // Bottom bar appearance.
//        bottomBar.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
//        bottomBar.setAccentColor(ContextCompat.getColor(this, R.color.colorAccent));
//        bottomBar.setInactiveColor(ContextCompat.getColor(this, R.color.bluegrey));
//        bottomBar.setBehaviorTranslationEnabled(true);
//        presenter.setUpBottomBar(bottomBar);
    }

    private void setUpTabs() {
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setCorrectTabFragment(int tab) {

        // Rather than set correct tab, this shold probably be set correct role.
        // currentTab.setRole(role)
        tabAdapter.setRole(tab);
    }

    @Override
    public void ShowOverlay() {
        overlay.setVisibility(View.VISIBLE);
    }

    @Override
    public void HideOverlay() {
        overlay.setVisibility(View.GONE);
    }

    @Override
    public void SetChampFabIconAsACross() {
        champFab.setImageResource(R.drawable.ic_cancel_white_24px);
    }

    @Override
    public void SetChampFabIconAsNone() {

    }

    @Override
    public void SetChampFabIconAsSelectedChamp(String champIconUrl) {
        Picasso.with(this).load(R.drawable.khazix).into(champFab);
        champFab.setBorderColor(ContextCompat.getColor(this, R.color.colorAccent));
    }

    @Override
    public void ShowChampList() {
        champList.setVisibility(View.VISIBLE);
    }

    @Override
    public void HideChampList() {
        champList.setVisibility(View.GONE);
    }

    @Override
    public void ShowSearchBar() {
        searchBox.setVisibility(View.VISIBLE);
    }

    @Override
    public void HideSearchBar() {
        searchBox.setVisibility(View.GONE);
    }

    @Override
    public void SetChampList(ArrayList<Champ> champs) {
        SearchListAdapter searchListAdapter = new SearchListAdapter(searchPresenter);
        searchListAdapter.SetData(champs);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this);
        horizontalLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        champList.setLayoutManager(horizontalLayoutManager);
        champList.setAdapter(searchListAdapter);
    }

    @Override
    public void ensureKeyboardIsHidden() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @OnClick(R.id.champ_fab) void handleChampFilterClick() {
        searchPresenter.handleSearchFabClick();
    }

    @OnClick(R.id.overlay) void consumeOverlayClicks() {
        // Probably just hide when this happens
    }


    /**
     * Required for our text watcher for the search. Do not remove
     * @param charSequence
     * @param i
     * @param i1
     * @param i2
     */
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    /**
     * Handle a text input into our search box. Update on each key
     * @param charSequence
     * @param i
     * @param i1
     * @param i2
     */
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        searchPresenter.searchForChamp(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
