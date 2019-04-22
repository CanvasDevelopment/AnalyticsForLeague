package com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards

/**
 * @author Josiah Kendall
 *
 * Contract for the cards in the
 */

interface MatchHistoryCardViewContract {

    fun setDetailsUrl(detailsUrl : String)
    fun getDetailsUrl() : String
    fun setGraph1(HeadToHeadStat: HeadToHeadStat)
    fun setGraph2(HeadToHeadStat: HeadToHeadStat)
    fun setGraph3(HeadToHeadStat: HeadToHeadStat)
    fun setGraph4(HeadToHeadStat: HeadToHeadStat)
    fun setHeroChampIcon(champIconUrl: String)
    fun setEnemyChampIcon(champIconUrl: String)
    fun setTimeStamp(timeStamp: String)
    fun setResult(result: String)
    fun setChamp(champName: String)
    fun setSummaryChart(HeadToHeadStat: HeadToHeadStat)
    fun setChampPlaceHolders()
}
