package com.teamunemployment.lolanalytics.front_page.tabs.StatTab.recycler

import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.HeadToHeadStat

/**
 * Created by Josiah Kendall
 */
interface StatCardContract {
    fun onStatLoaded(stats : ArrayList<HeadToHeadStat>)
    fun setTitle(title : String)
}