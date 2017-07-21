package com.teamunemployment.lolanalytics.FrontPage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.teamunemployment.lolanalytics.FrontPage.Tabs.CoachTab.CoachView;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.MatchHistoryTab.MatchHistoryTabView;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.PlayerAnalysisTab.PlayerAnalysisView;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.StatsComparisonTab.TabView;
import com.teamunemployment.lolanalytics.FrontPage.Tabs.TabContract;

/**
 * @author Josiah Kendall.
 */
public class TabAdapter extends FragmentPagerAdapter {

    private TabContract.View currentTab;

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Set the current role for our tab. The tab will handle the rest
     * @param role
     */
    public void setRole(int role) {
        currentTab.setRole(role);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        Fragment resultFragment = null;

        if (position == 0) {
            resultFragment = new MatchHistoryTabView();
        } else if (position == 1) {

            resultFragment = new PlayerAnalysisView();
        }

        currentTab = (TabContract.View) resultFragment;

        return resultFragment;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Statistics";
            case 1:
                return "Analyse";
        }
        return null;
    }
}


