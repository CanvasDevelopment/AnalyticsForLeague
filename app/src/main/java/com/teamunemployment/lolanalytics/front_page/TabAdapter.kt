package com.teamunemployment.lolanalytics.front_page

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.AnalyseTabView
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.MatchHistoryTabView
import com.teamunemployment.lolanalytics.front_page.tabs.TabContract
import com.teamunemployment.lolanalytics.Utils.Constant

/**
 * @author Josiah Kendall.
 */
class TabAdapter(val fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var currentTab: TabContract.View? = null
    private val role = 1

    override fun getItem(position: Int): Fragment? {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        var resultFragment: Fragment? = null

        if (position == 0) {
            resultFragment = MatchHistoryTabView()
        } else if (position == 1) {
            resultFragment = AnalyseTabView()
            val args = Bundle()
            args.putInt(Constant.ROLE_KEY, 1)
            resultFragment.arguments = args
        }

        currentTab = resultFragment as TabContract.View?

        return resultFragment
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "History"
            1 -> return "Analyse"
        }
        return null
    }
}


