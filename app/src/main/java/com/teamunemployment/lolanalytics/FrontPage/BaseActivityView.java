package com.teamunemployment.lolanalytics.FrontPage;

import android.graphics.Color;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.teamunemployment.lolanalytics.App;
import com.teamunemployment.lolanalytics.Data.model.Champ;
import com.teamunemployment.lolanalytics.FrontPage.Search.SearchContract;
import com.teamunemployment.lolanalytics.FrontPage.Search.SearchPresenter;
import com.teamunemployment.lolanalytics.R;


import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Josiah Kendall.
 *
 * This is the base class of the main application page. Holds the bottom bar and the fragment viewpager.
 */
public class BaseActivityView extends AppCompatActivity implements BaseActivityContract.View, SearchContract.View {

    private TabAdapter tabAdapter;

    @Inject
    public BaseActivityPresenter presenter;

    @Inject
    public SearchPresenter searchPresenter;
//    @Bind(R.id.bottomBar) AHBottomNavigation bottomBar;

    @Bind(R.id.container) ViewPager viewPager;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.champ_fab) FloatingActionButton champFab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);

        ((App) getApplication()).getNetComponent().InjectView(this);

        ButterKnife.bind(this);
        presenter.setView(this);

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

    }

    @Override
    public void HideOverlay() {

    }

    @Override
    public void SetChampFabIconAsACross() {

    }

    @Override
    public void SetChampFabIconAsNone() {

    }

    @Override
    public void SetChampFabIconAsSelectedChamp(String champIconUrl) {

    }

    @Override
    public void ShowChampList() {

    }

    @Override
    public void HideChampList() {

    }

    @Override
    public void ShowSearchBar() {

    }

    @Override
    public void HideSearchBar() {

    }

    @Override
    public void SetChampList(ArrayList<Champ> champs) {

    }

    @OnClick(R.id.champ_fab) void handleChampFilterClick() {
        searchPresenter.handleSearchClick();
    }
}
