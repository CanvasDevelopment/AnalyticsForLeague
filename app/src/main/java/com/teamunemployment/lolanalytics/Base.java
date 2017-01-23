package com.teamunemployment.lolanalytics;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.ncapdevi.fragnav.FragNavController;
import com.teamunemployment.lolanalytics.Data.Statics;
import com.teamunemployment.lolanalytics.StatsComparisonTab.TabView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Josiah Kendall.
 *
 * This is the base class of the application. Holds the bottom bar and the fragment view.
 */
public class Base extends AppCompatActivity {

    private FragNavController fragNavController;

    @Bind(R.id.bottomBar) AHBottomNavigation bottomBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);
        ButterKnife.bind(this);
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
                switch (position) {
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
                        // SUPPPORT
                        fragNavController.switchTab(FragNavController.TAB5);
                        break;
                }
                return true;
            }
        });
    }

    /**
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
}
