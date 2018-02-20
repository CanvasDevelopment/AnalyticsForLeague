package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen.model

import android.widget.TextView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData

/**
 * Created by Josiah Kendall
 */
class GameStageView(private val title : TextView,
                    private val chart1 : PieChart,
                    private val chart1Title : TextView,
                    private val chart2: PieChart,
                    private val chart2Title : TextView,
                    private val chart3: PieChart,
                    private val chart3Title : TextView,
                    private val chart4: PieChart,
                    private val chart4Title : TextView,
                    private val chart5: PieChart,
                    private val chart5Title : TextView,
                    private val chart6: PieChart,
                    private val chart6Title : TextView) {


    fun setChart1(data : PieData) {
        chart1.data = data
    }

    fun setChart2(data : PieData) {
        chart2.data = data
    }

    fun setChart3(data : PieData) {
        chart3.data = data
    }

    fun setChart4(data : PieData) {
        chart4.data = data
    }

    fun setChart5(data : PieData) {
        chart5.data = data
    }

    fun setChart6(data : PieData) {
        chart6.data = data
    }

    fun setViewTitle(title : String) {
        this.title.text = title
    }

    fun setChartTitle1(data : String) {
        chart1Title.text = data
    }

    fun setChartTitle2(data : String) {
        chart2Title.text = data
    }

    fun setChartTitle3(data : String) {
        chart3Title.text = data
    }

    fun setChartTitle4(data : String) {
        chart4Title.text = data
    }

    fun setChartTitle5(data : String) {
        chart5Title.text = data
    }

    fun setChartTitle6(data : String) {
        chart6Title.text = data
    }
}