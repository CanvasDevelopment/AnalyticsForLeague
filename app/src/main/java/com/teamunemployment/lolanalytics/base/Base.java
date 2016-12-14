package com.teamunemployment.lolanalytics.base;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.ncapdevi.fragnav.FragNavController;
import com.teamunemployment.lolanalytics.Jungle.JungleView;
import com.teamunemployment.lolanalytics.R;
import com.teamunemployment.lolanalytics.Top.TopView;

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
        setContentView(R.layout.main);
        ButterKnife.bind(this);
        setUpFragments(savedInstanceState);
        setUpBottomBar();
    }

    /**
     * Set up colors and buttons and click handlers
     */
    private void setUpBottomBar() {

        // Bottom bar setup.
        bottomBar.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        bottomBar.setAccentColor(ContextCompat.getColor(this, R.color.colorAccent));

        // Set up bottom bar items.
        AHBottomNavigationItem topItem = new AHBottomNavigationItem("Top", R.drawable.top);
        AHBottomNavigationItem jgItem = new AHBottomNavigationItem("Jungle", R.drawable.jg);
        AHBottomNavigationItem midItem = new AHBottomNavigationItem("Mid", R.drawable.mid);
        AHBottomNavigationItem adcItem = new AHBottomNavigationItem("Bottom", R.drawable.bot);
        AHBottomNavigationItem supItem = new AHBottomNavigationItem("Support", R.drawable.sup);

        bottomBar.addItem(topItem);
        bottomBar.addItem(jgItem);
        bottomBar.addItem(midItem);
        bottomBar.addItem(adcItem);
        bottomBar.addItem(supItem);

        // Click listener for the tabs.
        bottomBar.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {

            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0:
                        fragNavController.switchTab(FragNavController.TAB1);
                        break;
                    case 1:
                        fragNavController.switchTab(FragNavController.TAB2);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Create fragments, and add them to our frag controller.
     * @param savedInstanceState
     */
    private void setUpFragments(Bundle savedInstanceState) {
        List<Fragment> fragments = new ArrayList<>(4);
        // fragments.add(new HomeTabFragment());
        TopView topView = new TopView();
        JungleView jungleView = new JungleView();

        fragments.add(topView);
        fragments.add(jungleView);
        fragments.add(jungleView);
        fragments.add(jungleView);
        fragNavController = new FragNavController(savedInstanceState, getSupportFragmentManager(),R.id.fragment_container,fragments, FragNavController.TAB1);
        fragNavController.clearStack();
    }
}
