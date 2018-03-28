package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Details

import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.DetailsScreen.DetailsInteractor
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.DetailsScreen.DetailsPresenter
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.DetailsScreen.DetailsView
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.DetailsScreen.model.StatDetailsDataModel
import org.junit.Test
import org.mockito.Mockito.*

/**
 * Created by Josiah Kendall
 */
class StatDetailsPresenterTests {

    private val interactor = mock(DetailsInteractor::class.java)
    private val presenter = DetailsPresenter(interactor)
    private val view = mock(DetailsView::class.java)
    @Test
    fun `Test that we set the view correctly`() {

        presenter.setView(view)
        val history = ArrayList<ArrayList<Int>>()
        val opponent = ArrayList<HeadToHeadStat>()
        opponent.add(mock(HeadToHeadStat::class.java))
        opponent.add(mock(HeadToHeadStat::class.java))
        opponent.add(mock(HeadToHeadStat::class.java))
        val division = ArrayList<HeadToHeadStat>()
        division.add(mock(HeadToHeadStat::class.java))
        division.add(mock(HeadToHeadStat::class.java))
        division.add(mock(HeadToHeadStat::class.java))
        val details = StatDetailsDataModel(history,opponent,division)
        presenter.onDetailsLoaded(details)
        verify(view, times(1)).setVsOpponentGameStageCharts(opponent)
        verify(view, times(1)).setVsDivisionGameStageCharts(division)
    }
}