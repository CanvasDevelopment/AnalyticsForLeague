package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.DetailsScreen.model

import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.HeadToHeadStat

/**
 * Created by Josiah Kendall
 */
data class StatDetailsDataModel(val historical : ArrayList<ArrayList<Int>>,
                                val vsOpponent : ArrayList<HeadToHeadStat>,
                                val vsDivision : ArrayList<HeadToHeadStat>)