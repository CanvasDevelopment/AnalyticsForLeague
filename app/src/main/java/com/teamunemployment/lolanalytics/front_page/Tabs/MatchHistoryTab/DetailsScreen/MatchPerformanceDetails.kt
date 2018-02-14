package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen

import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.GameStageStat

/**
 * Created by Josiah Kendall
 *
 * Pojo for the details for a match performance for a user
 */
class MatchPerformanceDetails(val kda : ArrayList<GameStageStat>,
                              val creeps : ArrayList<GameStageStat>,
                              val damageDealt : ArrayList<GameStageStat>,
                              val damagetaken : ArrayList<GameStageStat>,
                              val gold : ArrayList<GameStageStat>,
                              val xp : ArrayList<GameStageStat>)