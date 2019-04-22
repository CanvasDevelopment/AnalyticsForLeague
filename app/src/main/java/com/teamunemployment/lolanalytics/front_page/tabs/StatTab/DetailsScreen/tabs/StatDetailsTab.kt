package com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen.tabs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.data.LineData
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.data.model.SummonerRapidAccessObject
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.MatchHistoryPresenter
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen.DetailsView
import kotlinx.android.synthetic.main.stat_details_tab.*
import org.koin.android.ext.android.inject

class StatDetailsTab : Fragment() {


    private var rootView: View? = null

    val presenter: StatDetailsTabPresenter by inject() // should probably use a different presenter
    val summonerRapidAccessObject: SummonerRapidAccessObject by inject()
    lateinit var baseActivity : DetailsView
    // game stage should be a combo of the stat and the gamestage
    var statString = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.rootView = inflater.inflate(R.layout.stat_details_tab, container, false)

        presenter.setView(this)
        presenter.start()
        baseActivity = activity as DetailsView
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun setLineChart(lineData : LineData) {
        statHistory.data = lineData
        // todo style graph here
    }

    fun setMax(max : Float) {
        statMax.text = "Max: $max"
    }

    fun setMin(min : Float) {
        statMin.text = "Min: $min"
    }

    fun setAvg(avg : Float) {
        statAverage.text = "Avg: $avg"
    }

    fun showMessage(message : String) {
        baseActivity.showMessage(message)
    }
}