package com.teamunemployment.lolanalytics.Utils

import android.content.Context
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.DetailsScreen.MatchPerformanceDetails
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Model.MatchHistoryCardData
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

fun HeadToHeadStat.producePieChartData(context :Context): PieData {
    val entries = java.util.ArrayList<PieEntry>()

    entries.add(PieEntry(heroStatValue, "Hero"))
    entries.add(PieEntry(enemyStatValue, "Enemy"))

    val pieDataSet = PieDataSet(entries, "")
    pieDataSet.valueTextColor = context.resources.getColor(R.color.grey)
    pieDataSet.setColors(intArrayOf(R.color.teal, R.color.pink), context) // Would be real cool to not use context here if possible.

    return PieData(pieDataSet)
}

fun Float.calculatePercentageDifference(float: Float) : Float {
    val difference = this - float
    val total = this + float
    return difference / total
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