package com.teamunemployment.lolanalytics.Utils

import android.content.Context
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen.MatchPerformanceDetails
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Model.MatchHistoryCardData
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Model.PieReadyComparisonResult
import retrofit2.Response

/**
 * @author Josiah Kendall
 */
fun Response<Result<ArrayList<String>>>.getMatchIds() : Result<ArrayList<String>> {
    if (body() != null) {
        return body()!!
    }

    return Result(404, ArrayList())
}

fun Response<Result<MatchHistoryCardData>>.getMatchHistoryCardData() : Result<MatchHistoryCardData> {
    if (body() != null) {
        return body()!!
    }

    return Result(404, MatchHistoryCardData(-1,
            -1,"",-1, HeadToHeadStat(0f,0f), HeadToHeadStat(0f,0f), HeadToHeadStat(0f,0f)))
}

fun Response<Result<MatchPerformanceDetails>>.getMatchPerformanceDetails() : Result<MatchPerformanceDetails> {
    if (body() != null) {
        return body()!!
    }

    return Result(404, MatchPerformanceDetails(ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList()))
}

fun HeadToHeadStat.producePieChartData(context :Context): PieReadyComparisonResult {
    val entries = java.util.ArrayList<PieEntry>()

    entries.add(PieEntry(enemyStatValue, ""))
    entries.add(PieEntry(heroStatValue, ""))

    val pieDataSet = PieDataSet(entries, "")
    pieDataSet.valueTextColor = context.resources.getColor(R.color.grey)
    pieDataSet.setColors(intArrayOf(R.color.pink, R.color.green), context) // Would be real cool to not use context here if possible.
    val pieData = PieData(pieDataSet)
    pieData.setValueTextColor(R.color.blue)
    pieData.setValueTextSize(10f)

    return PieReadyComparisonResult(pieData, produceCentreText(heroStatValue, enemyStatValue).toString("%"))
}

fun Int.toString(appendage : String) : String {
    return this.toString() + appendage
}

/**
 * Style our pie chart.
 *
 * This is the default style that has the following characteristics
 */
fun PieChart.setDefaultStyle(centerText : String ) {
    // Set appearance params.
    val description = Description()
    description.text = ""
    description.isEnabled = false
    this.description = description
    this.isDrawHoleEnabled =true
    this.isRotationEnabled = false

    this.setDrawEntryLabels(false)
    this.centerText = centerText
    this.setCenterTextColor(R.color.green)
    this.legend.isEnabled = false
    // Refresh.
    this.invalidate()
    this.animateY(300)
}

fun Float.calculatePercentageDifference(float: Float) : Float {
    val difference = this - float
    val total = this + float
    return difference / total
}

/**
 * Calculate the center text value. This calculates the percentage of the total that the hero got.
 * @param heroVal   The total score of the hero
 * @param enemyVal  The total score of the enemy
 * @return the percentage the hero got, as an Int
 */
fun produceCentreText(heroVal : Float, enemyVal : Float) : Int {
    val total = heroVal + enemyVal
    return Math.round((heroVal/total)*100)
}

fun ArrayList<HeadToHeadStat>.earlyGame() : HeadToHeadStat {
    return this[0]
}

fun ArrayList<HeadToHeadStat>.midGame() : HeadToHeadStat {
    return this[1]
}

fun ArrayList<HeadToHeadStat>.lateGame() : HeadToHeadStat {
    return this[2]
}