package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model

import android.support.v7.widget.RecyclerView
import android.view.View

import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.AnalyseTabContract
import com.teamunemployment.lolanalytics.R

import java.util.ArrayList

import com.github.mikephil.charting.utils.ColorTemplate.rgb
import kotlinx.android.synthetic.main.analyse_card_item.view.*

/**
 * Created by Josiah Kendall
 *
 * This is the view item for
 */

class AnalyseCardViewHolder(private val view: View, private val presenter: AnalyseTabContract.Presenter) : RecyclerView.ViewHolder(view), AnalyseTabContract.CardView {

    var itemPosition: Int = 0

    init {
        view.cardBase.setOnClickListener { handleCardClick() }
    }

    private val pieChart = itemView.earlyGame

    override fun setTitle(title: String) {
        view.analyseCardTitle.text = title
    }

    override fun setEarlyGame(hero: Float, enemy: Float) {

    }

    override fun setMidGame(hero: Float, enemy: Float) {

    }

    override fun setLateGame(hero: Float, enemy: Float) {

    }

    //    @Override
    @Deprecated("")
    fun SetGraph(enemyStat: Float, heroStat: Float) {

        // set the performance pie here
        val entries = ArrayList<PieEntry>()

        entries.add(PieEntry(enemyStat, "Opponent performance"))
        entries.add(PieEntry(heroStat, "My performance"))

        val set = PieDataSet(entries, "")
        val MATERIAL_COLORS = intArrayOf(rgb("#FF3D00"), rgb("#00E676"))
        set.setColors(*MATERIAL_COLORS)
        set.setDrawValues(false)
        val data = PieData(set)
        pieChart.data = data
        pieChart.setDrawMarkers(false)
        pieChart.setUsePercentValues(false)
        pieChart.setDrawSliceText(false)
        pieChart.isHighlightPerTapEnabled = false

        pieChart.centerText = Integer.toString(heroStat.toInt()) + "%"
        pieChart.setCenterTextColor(R.color.colorAccent)
        pieChart.setDrawEntryLabels(false)

        // Dont want to display a description.
        val description = Description()
        description.text = ""
        pieChart.description = description

        // Customize legend.
        pieChart.legend.form = Legend.LegendForm.CIRCLE
        pieChart.legend.isEnabled = false
        pieChart.invalidate()
    }

    //    @Override
    @Deprecated("")
    fun setChange(change: Double) {
//        performanceChange!!.text = java.lang.Double.toString(change)
    }

    private fun handleCardClick() {
        presenter.handleItemClick(position)
    }
}
