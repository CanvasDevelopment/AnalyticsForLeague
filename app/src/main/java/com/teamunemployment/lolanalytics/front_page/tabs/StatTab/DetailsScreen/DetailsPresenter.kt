package com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen

import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen.model.StatDetailsDataModel

/**
 * Created by Josiah Kendall
 */
class DetailsPresenter(private val detailsInteractor: DetailsInteractor) {

    private lateinit var view : DetailsView


    fun showMessage(message : String) {
        view.showMessage(message)
    }

    fun setView(view: DetailsView) {
        this.view = view
    }

    /**
     *  Start loading the data that we need.
     */
    fun start(url : String) {
        detailsInteractor.fetchDetails(url, this)
    }

    fun onDetailsLoaded(dataStat: StatDetailsDataModel) {
        view.setVsDivisionGameStageCharts(dataStat.vsDivision)
        view.setVsOpponentGameStageCharts(dataStat.vsOpponent)
        // process the dataStat into the form we need for the view
        //view.setHistoricalLineChart
        //view.setVsOpponent charts
        //view.setVsDivision charts

    }
}