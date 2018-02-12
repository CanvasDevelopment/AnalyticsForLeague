package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen

/**
 * @author Josiah Kendall
 */
class DetailsPresenter(private val detailsInteractor: DetailsInteractor) {

    private lateinit var view : DetailsView

    fun setView(view : DetailsView) {
        this.view = view
    }

    /**
     * Trigger the loading of the data, which will be presented to the view once it is ready
     * @param matchId       The unique id of the match that we are fetching the data for.
     * @param summonerId    The unique Id of the summoner who is the hero in this match
     * @param role          The role of the summoner that we are interested in.
     */
    fun start(matchId : Long, summonerId : Long, role : String) {

    }
}