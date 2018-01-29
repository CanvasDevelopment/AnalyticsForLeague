package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Model


import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.CardData

/**
 * @author Josiah Kendall
 */

class MatchHistoryCardData {

    var matchId: Long = 0
    var champId: Long = 0
    var champName: String? = null
    var kills: CardData? = null
    var deaths: CardData? = null
    var csFirstTen: CardData? = null
    var csSecondTen: CardData? = null
    var csTotal: CardData? = null

    // Default
    constructor() {}

    //
    constructor(champId: Long, champName: String, kills: CardData, deaths: CardData, csFirstTen: CardData, csSecondTen: CardData, csTotal: CardData, matchId: Long) {
        this.champId = champId
        this.champName = champName
        this.kills = kills
        this.csFirstTen = csFirstTen
        this.csSecondTen = csSecondTen
        this.csTotal = csTotal
        this.deaths = deaths
        this.matchId = matchId
    }
}
