package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.recycler

import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.HeadToHeadStat

/**
 * Created by Josiah Kendall
 */
interface StatCardContract {
    fun onStatLoaded(stats : ArrayList<HeadToHeadStat>)
    fun setTitle(title : String)
}