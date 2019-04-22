package com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen.model

import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.HeadToHeadStat

/**
 * Created by Josiah Kendall
 */
data class StatDetailsDataModel(val historical : ArrayList<ArrayList<Int>>,
                                val vsOpponent : ArrayList<HeadToHeadStat>,
                                val vsDivision : ArrayList<HeadToHeadStat>)