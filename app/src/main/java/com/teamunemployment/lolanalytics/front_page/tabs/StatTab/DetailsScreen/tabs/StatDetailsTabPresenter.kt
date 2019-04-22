package com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen.tabs

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.teamunemployment.lolanalytics.Utils.Role
import com.teamunemployment.lolanalytics.data.model.SummonerRapidAccessObject
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.HeadToHeadStat

class StatDetailsTabPresenter(private val summonerRapidAccessObject: SummonerRapidAccessObject,
                              private val statInteractor : StatDetailsTabInteractor) {

    private lateinit var view : StatDetailsTab
    private val roleHelper = Role()
    fun setView(view : StatDetailsTab) {
        this.view = view
    }

    fun start() {
        val champkey = summonerRapidAccessObject.champKey
        val summonerId = summonerRapidAccessObject.summonerId
        val role = summonerRapidAccessObject.role
        if (summonerRapidAccessObject.champKey != -1) {
            statInteractor.loadStats(summonerId,50,roleHelper.produceSingleLaneStringForRoleInt(role), view.statString, champkey,this)
        } else {
            statInteractor.loadStats(summonerId,50,roleHelper.produceSingleLaneStringForRoleInt(role), view.statString, this)
        }
    }
    // handle return of data
    //  - this needs to set the average, min, max and graph.
    // setting the line chart may require some construction of variables like the pie charts

    /**
     * Handle the raw line data that comes back from the server. Currently only creates a hero line,
     * but could be easily used to create a enemy line too.
     *
     * @param rawData The raw history data of the stat that we are fetching. This comes as a [HeadToHeadStat]
     *                with both the hero and enemy data.
     * TODO make it an average, not just raw data
     */
    fun handleRawLineData(rawData: ArrayList<HeadToHeadStat>) {
        // now I need to create a line of hero rawData
        val entries = ArrayList<Entry>()
        var count = 0f

        // Create a list of entries based off of our raw hero data
        for (dataObject in rawData) {
            entries.add(Entry(count, dataObject.heroStatValue))
            count += 1
        }

        val lineDataSet = LineDataSet(entries,  "Hero")

//        view.setLineChart(LineData(lineDataSet))
    }

    fun handleAverage(avg : Float) {
        view.setAvg(avg)
    }

    fun handleMinimum(min : Float) {
        view.setMin(min)
    }

    fun handleMaximum(max : Float) {
        view.setMax(max)
    }

    fun showMessage(message : String) {
        view.showMessage(message)
    }

}