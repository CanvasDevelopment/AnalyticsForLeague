package com.teamunemployment.lolanalytics.Utils

import android.content.Context
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.TextBarChartRenderer
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.DetailsScreen.MatchPerformanceDetails
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.MatchHistoryCardData
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.MatchIdentifier
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.PieReadyComparisonResult
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.DetailsScreen.model.StatDetailsDataModel
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.Model.StatSummary
import retrofit2.Response

/**
 * @author Josiah Kendall
 */
fun Response<Result<ArrayList<MatchIdentifier>>>.getMatchIds() : Result<ArrayList<MatchIdentifier>> {
    if (body() != null) {
        return body()!!
    }

    return Result(404, ArrayList())
}

fun Response<Result<ArrayList<StatSummary>>>.getStatList() : Result<ArrayList<StatSummary>> {
    if (body() != null) {
        return body() !!
    }

    return Result(404, ArrayList())
}

fun Response<Result<ArrayList<HeadToHeadStat>>>.getHeadToHeadStats() : Result<ArrayList<HeadToHeadStat>> {
    if (body() != null) {
        return body() !!
    }

    return Result(404, ArrayList())
}

fun Response<Result<MatchHistoryCardData>>.getMatchHistoryCardData() : Result<MatchHistoryCardData> {
    if (body() != null) {
        return body()!!
    }

    return Result(404, MatchHistoryCardData(-1,
            -1,-1, HeadToHeadStat(0f,0f), HeadToHeadStat(0f,0f), HeadToHeadStat(0f,0f), false, "", "-1"))
}

fun Response<Result<MatchPerformanceDetails>>.getMatchPerformanceDetails() : Result<MatchPerformanceDetails> {
    if (body() != null) {
        return body()!!
    }

    return Result(404, MatchPerformanceDetails(ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList()))
}

fun Response<Result<StatDetailsDataModel>>.getStatDetails() : Result<StatDetailsDataModel> {
    if (body() != null) {
        return body()!!
    }

    return Result(404, StatDetailsDataModel(ArrayList(), ArrayList(),ArrayList()))
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

fun HeadToHeadStat.produceBarData(context :Context): BarData {
    val entries = java.util.ArrayList<BarEntry>()

    entries.add(BarEntry(2f, enemyStatValue))
    entries.add(BarEntry(1f,heroStatValue))

    val barDataSet = BarDataSet(entries, "")
    barDataSet.valueTextColor = context.resources.getColor(R.color.grey)
    barDataSet.setColors(intArrayOf(R.color.oldColorPrimary, R.color.green), context) // Would be real cool to not use context here if possible.
    val barData = BarData(barDataSet)
    barData.setValueTextColor(R.color.blue)
    barData.setValueTextSize(10f)

    return barData
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

fun BarChart.setDefaultStyle() {
    val description = Description()
    description.text = ""
    description.isEnabled = false

    this.description = description
    setPinchZoom(false)
    setTouchEnabled(false)
    this.setDrawGridBackground(false)
    this.axisLeft.setDrawLimitLinesBehindData(false)
    this.axisLeft.setDrawLabels(false)
    this.axisLeft.setDrawAxisLine(false)
    this.axisLeft.setDrawGridLines(false)
    this.axisLeft.axisMinimum = 0f

    this.axisRight.setDrawLimitLinesBehindData(false)
    this.axisRight.setDrawLabels(false)
    this.axisRight.setDrawGridLines(false)
    this.axisRight.setDrawAxisLine(false)
    this.axisRight.axisMinimum = 0f

    this.xAxis.setDrawLimitLinesBehindData(false)
    this.xAxis.setDrawLabels(false)
    this.xAxis.setDrawGridLines(false)
    this.xAxis.setDrawAxisLine(false)
//    this.renderer = TextBarChartRenderer(this,animator,viewPortHandler)
    this.legend.isEnabled = false
    this.invalidate()
}

/**
 * Style our pie chart.
 *
 * This is the default style that has the following characteristics
 *  - No numbers on the slices
 *  - Middle text
 *  - No animation
 */
fun PieChart.setSimpleStyle(centerText : String ) {
    // Set appearance params.
    val description = Description()
    description.text = ""
    description.isEnabled = false
    this.description = description
    this.isDrawHoleEnabled =true
    this.setDrawEntryLabels(false)
    this.isRotationEnabled = false
    this.data.setDrawValues(false)

    this.setDrawEntryLabels(false)
//    this.
    this.centerText = centerText
    this.setCenterTextColor(R.color.green)
    this.legend.isEnabled = false
    // Refresh.
    this.invalidate()
//    this.animateY(300)
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

