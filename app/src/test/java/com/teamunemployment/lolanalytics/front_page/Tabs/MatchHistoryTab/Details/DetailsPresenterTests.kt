package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Details

import android.content.Context
import android.view.ViewGroup
import com.teamunemployment.lolanalytics.Utils.Constant.*
import com.teamunemployment.lolanalytics.Utils.Constant.Companion.EARLY_GAME
import com.teamunemployment.lolanalytics.Utils.Constant.Companion.LATE_GAME
import com.teamunemployment.lolanalytics.Utils.Constant.Companion.MID_GAME
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen.DetailsInteractor
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen.DetailsPresenter
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen.ViewProducer
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen.model.GameStageView
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

/**
 * Created by Josiah Kendall
 */
class DetailsPresenterTests {

    val context = mock(Context::class.java)
    val detailsInteractor = mock(DetailsInteractor::class.java)
    val viewProducer = mock(ViewProducer::class.java)
    val detailsPresenter = DetailsPresenter(detailsInteractor,context,viewProducer)

    @Test
    fun makeSureThatWeShowErrorMessageWhenWeRecieve500() {

    }

    @Test
    fun makeSureThatWeShowCorrectErrorMessageWhenWeRecieve404() {

    }

    @Test
    fun `Make sure that we set the correct titles for the different game stages`() {
        val mockGameStageView = mock(GameStageView::class.java)
        val viewGroup = mock(ViewGroup::class.java)
        `when`(viewProducer.produceGameStageView(viewGroup)).thenReturn(mockGameStageView)
        detailsPresenter.produceGameStageView(EARLY_GAME, viewGroup)
        verify(mockGameStageView, times(1)).setViewTitle("Early Game")
        verify(mockGameStageView, times(0)).setViewTitle("Mid Game")
        verify(mockGameStageView, times(0)).setViewTitle("Late Game")

        detailsPresenter.produceGameStageView(MID_GAME, viewGroup)
        verify(mockGameStageView, times(1)).setViewTitle("Early Game")
        verify(mockGameStageView, times(1)).setViewTitle("Mid Game")
        verify(mockGameStageView, times(0)).setViewTitle("Late Game")

        detailsPresenter.produceGameStageView(LATE_GAME, viewGroup)
        verify(mockGameStageView, times(1)).setViewTitle("Early Game")
        verify(mockGameStageView, times(1)).setViewTitle("Mid Game")
        verify(mockGameStageView, times(1)).setViewTitle("Late Game")
    }
}