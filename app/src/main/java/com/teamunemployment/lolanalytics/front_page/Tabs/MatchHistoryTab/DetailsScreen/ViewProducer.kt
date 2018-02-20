package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen.model.GameStageView
import kotlinx.android.synthetic.main.game_stage_details_section.*
import kotlinx.android.synthetic.main.game_stage_details_section.view.*

/**
 * Created by Josiah Kendall
 *
 * A class to produce a view so that it can be easily unit tested.
 */
class ViewProducer(private val inflater: LayoutInflater) {

    /**
     * Produce a [GameStageView]. This sets the required elements onto the view for use to use.
     *
     * @return a [GameStageView]
     */
    fun produceGameStageView(parent : ViewGroup) : GameStageView {
        val gameStageView = inflater.inflate(R.layout.game_stage_details_section, parent)
        return GameStageView(gameStageView.gameStageTitle,
                gameStageView.stat1,
                gameStageView.stat1Title,
                gameStageView.stat2,
                gameStageView.stat2Title,
                gameStageView.stat3,
                gameStageView.stat3Title,
                gameStageView.stat4,
                gameStageView.stat4Title,
                gameStageView.stat5,
                gameStageView.stat5Title,
                gameStageView.stat6,
                gameStageView.stat6Title)
    }
}