package com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab

import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.MatchHistoryCardViewContract
import com.teamunemployment.lolanalytics.PresenterContract
import com.teamunemployment.lolanalytics.data.model.MatchSummary
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.MatchHistoryCardData
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.MatchIdentifier
import com.teamunemployment.lolanalytics.front_page.tabs.TabContract

import java.util.ArrayList

/**
 * @author Josiah Kendall
 */
class MatchHistoryTabContract {

    interface Presenter : PresenterContract {
        fun showMessage(message : String)
        fun loadDataForRole(role: Int, summonerId : String)
        fun loadMatchSummary(matchId : Long)
        fun setView(view : TabContract.View)
        fun onMatchSummaryLoadedSuccessfully(matchSummary : MatchSummary)
        fun onError(e : Throwable)
        fun onMatchListLoadedSuccessfully(result: ArrayList<MatchIdentifier>, code : Int)
        fun setRole(role: Int)
        fun loadCardData(id: MatchIdentifier, cardViewContract : MatchHistoryCardViewContract, region : String)
        fun setLoadedCardData(matchHistoryCardData: MatchHistoryCardData, cardViewContract: MatchHistoryCardViewContract, timestamp: Long, resultString: String)
    }
}
