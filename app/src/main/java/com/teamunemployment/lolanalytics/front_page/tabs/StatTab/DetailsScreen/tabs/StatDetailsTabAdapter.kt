package com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen.tabs

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class StatDetailsTabAdapter(val fm: FragmentManager)  : FragmentPagerAdapter(fm) {

    private var currentTab: StatDetailsTab? = null
    var statName = ""

    override fun getItem(position: Int): Fragment? {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        val resultFragment  = StatDetailsTab()

        when (position) {
            0 -> resultFragment.statString = "${statName}EarlyGame" // + current stat
            1 -> resultFragment.statString = "${statName}MidGame" // + current stat
            2 -> resultFragment.statString = "${statName}LateGame" // + current stat
        }

        // just return a fragment
        currentTab = resultFragment

        return resultFragment
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Early Game"
            1 -> return "Mid Game"
            2 -> return "Late Game"
        }
        return null
    }
}