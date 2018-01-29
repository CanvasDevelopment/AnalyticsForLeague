package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab

import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.MatchHistoryCardViewContract
import com.teamunemployment.lolanalytics.PresenterContract
import com.teamunemployment.lolanalytics.data.model.MatchIdWrapper
import com.teamunemployment.lolanalytics.data.model.MatchSummary
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Model.MatchHistoryCardData
import com.teamunemployment.lolanalytics.front_page.Tabs.TabContract

import java.util.ArrayList

/**
 * @author Josiah Kendall
 */
class MatchHistoryTabContract {

    interface Presenter : PresenterContract {
        fun loadDataForRole(role: Int, summonerId: Long)
        fun loadMatchSummary(matchId: Long)
        fun setView(view: TabContract.View)
        fun onMatchSummaryLoadedSuccessfully(matchSummary: MatchSummary)
        fun onError(e: Throwable)
        fun onMatchListLoadedSuccessfully(matchHistoryData: ArrayList<MatchIdWrapper>)
        fun setRole(role: Int)
        fun loadCardData(id: Long, cardViewContract: MatchHistoryCardViewContract)
        fun setLoadedCardData(matchHistoryCardData: MatchHistoryCardData, cardViewContract: MatchHistoryCardViewContract)
    }
}
