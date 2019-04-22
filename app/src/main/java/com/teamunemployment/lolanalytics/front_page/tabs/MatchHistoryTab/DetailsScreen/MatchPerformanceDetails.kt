package com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.DetailsScreen

import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.HeadToHeadStat

/**
 * Created by Josiah Kendall
 *
 * Pojo for the details for a match performance for a user
 */
class MatchPerformanceDetails(val kda : ArrayList<HeadToHeadStat>,
                              val creeps : ArrayList<HeadToHeadStat>,
                              val damageDealt : ArrayList<HeadToHeadStat>,
                              val damageTaken: ArrayList<HeadToHeadStat>,
                              val gold : ArrayList<HeadToHeadStat>,
                              val xp : ArrayList<HeadToHeadStat>)