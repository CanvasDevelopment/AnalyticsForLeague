package com.teamunemployment.lolanalytics.FrontPage;

import android.graphics.Color;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.ncapdevi.fragnav.FragNavController;
import com.teamunemployment.lolanalytics.App;
import com.teamunemployment.lolanalytics.Data.Statics;
import com.teamunemployment.lolanalytics.R;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.TabView;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Josiah Kendall.
 *
 * This is the base class of the main application page. Holds the bottom bar and the fragment tab view.
 */
public class BaseActivityView extends AppCompatActivity implements BaseActivityContract.View{

    private FragNavController fragNavController;

    @Inject
    public BaseActivityPresenter presenter;

    @Bind(R.id.bottomBar) AHBottomNavigation bottomBar;
    @Bind(R.id.win_rate_details) TextView winRateTextView;
    @Bind(R.id.user_name) TextView userNameTextView;
    @Bind(R.id.role_icon) CircleImageView roleIcon;
    @Bind(R.id.collapsable_toolbar_holder) CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);
        ((App) getApplication()).getNetComponent().InjectView(this);
        ButterKnife.bind(this);
        presenter.setView(this);

        // Defaults
        setRoleName("Top");
        setTabIcon(R.drawable.top);

        setUpFragments(savedInstanceState);
        setUpBottomBar();

    }

    /**
     * Set up colors and buttons and click handlers. Somewhat long method but there is no real logic.
     */
    private void setUpBottomBar() {

        // Bottom bar appearance.
        bottomBar.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        bottomBar.setAccentColor(ContextCompat.getColor(this, R.color.colorAccent));
        bottomBar.setInactiveColor(ContextCompat.getColor(this, R.color.bluegrey));
        // Bottom bar items.
        AHBottomNavigationItem topItem = new AHBottomNavigationItem("Top", R.drawable.top);
        AHBottomNavigationItem jgItem = new AHBottomNavigationItem("Jungle", R.drawable.jg);
        AHBottomNavigationItem midItem = new AHBottomNavigationItem("Mid", R.drawable.mid);
        AHBottomNavigationItem adcItem = new AHBottomNavigationItem("Bottom", R.drawable.bot);
        AHBottomNavigationItem supItem = new AHBottomNavigationItem("Support", R.drawable.sup);

        // Add items to bottom bar.
        bottomBar.addItem(topItem);
        bottomBar.addItem(jgItem);
        bottomBar.addItem(midItem);
        bottomBar.addItem(adcItem);
        bottomBar.addItem(supItem);


        // Click listener for the bottom bar buttons.
        bottomBar.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {

            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                presenter.handleTabPress(position);
                return true;
            }
        });
    }

    /** TODO move to child tab
     * Create fragments for the different tabs, and add them to our frag controller.
     * @param savedInstanceState
     */
    private void setUpFragments(Bundle savedInstanceState) {
        List<Fragment> fragments = new ArrayList<>(4);

        // Our fragments are all the same, except for the data that they are loading. We set the desired data using setRole(ROLE)
        TabView topTabView = new TabView();
        topTabView.setRole(Statics.TOP);
        TabView jungleTabView = new TabView();
        jungleTabView.setRole(Statics.JUNGLE);
        TabView midTabView = new TabView();
        midTabView.setRole(Statics.MID);
        TabView adcTabView = new TabView();
        adcTabView.setRole(Statics.ADC);
        TabView supportTabView = new TabView();
        supportTabView.setRole(Statics.SUPPORT);

        fragments.add(topTabView);
        fragments.add(jungleTabView);
        fragments.add(midTabView);
        fragments.add(adcTabView);
        fragments.add(supportTabView);
        fragNavController = new FragNavController(savedInstanceState, getSupportFragmentManager(),R.id.fragment_container,fragments, FragNavController.TAB1);
        //refresh.
        fragNavController.clearStack();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setCorrectTabFragment(int tab) {

        // Get current viewpager item.
        // set role
        // refresh

        switch (tab) {
            case 0:
                // TOP
                fragNavController.switchTab(FragNavController.TAB1);
                break;
            case 1:
                // JUNGLE
                fragNavController.switchTab(FragNavController.TAB2);
                break;
            case 2:
                // MID
                fragNavController.switchTab(FragNavController.TAB3);
                break;
            case 3:
                // ADC
                fragNavController.switchTab(FragNavController.TAB4);
                break;
            case 4:
                // SUPPORT
                fragNavController.switchTab(FragNavController.TAB5);
                break;
        }
    }

    @Override
    public void setWinRate(double winRate) {
        String winRateString = winRate + "%";
        winRateTextView.setText(winRateString);
    }

    @Override
    public void setTabIcon(int icon) {
        int color = Color.parseColor("#ffffff");
        roleIcon.setColorFilter(color);
        roleIcon.setImageResource(icon);
    }

    @Override
    public void setRoleName(String string) {
        collapsingToolbar.setTitle(string);
    }

}
