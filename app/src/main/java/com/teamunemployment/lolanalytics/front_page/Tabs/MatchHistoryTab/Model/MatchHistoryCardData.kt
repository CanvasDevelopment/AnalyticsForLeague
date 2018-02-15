package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Model

import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.HeadToHeadStat

/**
 * @author Josiah Kendall
 */

class MatchHistoryCardData(val matchId: Long,
                           val champId: Long,
                           val champName: String,
                           val enemyChampId: Long,
                           val earlyGame: HeadToHeadStat,
                           val midGame: HeadToHeadStat,
                           val lateGame: HeadToHeadStat)
