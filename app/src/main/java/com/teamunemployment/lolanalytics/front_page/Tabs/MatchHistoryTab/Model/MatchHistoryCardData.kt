package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Model

import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.GameStageStat

/**
 * @author Josiah Kendall
 */

class MatchHistoryCardData(val matchId: Long,
                           val champId: Long,
                           val champName: String,
                           val enemyChampId: Long,
                           val earlyGame: GameStageStat,
                           val midGame: GameStageStat,
                           val lateGame: GameStageStat)
