package com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen.model

import android.widget.TextView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.Utils.setDefaultStyle

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

    fun setCreeps(data : PieData, centerText : String, gameStageString : String) {
        chart1.data = data
        chart1Title.text = gameStageString
        chart1.setDefaultStyle(centerText)
    }

    fun setDamageDealt(data : PieData, centerText : String, gameStageString : String) {
        chart2.data = data
        chart2Title.text = gameStageString
        chart2.setDefaultStyle(centerText)
    }

    fun setDamageTaken(data : PieData, centerText : String, gameStageString : String ) {
        chart3.data = data
        chart3.setDefaultStyle(centerText)
        chart3Title.text = gameStageString
    }

    fun setXp(data : PieData, centerText : String, gameStageString : String) {
        chart4.data = data
        chart4.setDefaultStyle(centerText)
        chart4Title.text = gameStageString

    }

    fun setGold(data : PieData, centerText : String, gameStageString : String) {
        chart5.data = data
        chart5.setDefaultStyle(centerText)
        chart5Title.text = gameStageString

    }

    fun unused(data : PieData,centerText : String, gameStageString : String) {
        chart6.data = data
        chart6.setDefaultStyle(centerText)
        chart6Title.text = gameStageString

    }



    fun setViewTitle(title : String) {
        this.title.text = title
    }
}