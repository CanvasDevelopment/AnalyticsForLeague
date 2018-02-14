package com.teamunemployment.lolanalytics.Utils

import android.content.Context
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.GameStageStat
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
            -1,"",-1, GameStageStat(0f,0f), GameStageStat(0f,0f), GameStageStat(0f,0f)))
}

fun Response<Result<MatchPerformanceDetails>>.getMatchPerformanceDetails() : Result<MatchPerformanceDetails> {
    if (body() != null) {
        return body()!!
    }

    return Result(404, MatchPerformanceDetails(ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList()))
}

fun GameStageStat.producePieChartData(context :Context): PieData {
    val entries = java.util.ArrayList<PieEntry>()

    entries.add(PieEntry(heroStatValue, "Hero"))
    entries.add(PieEntry(enemyStatValue, "Enemy"))

    val pieDataSet = PieDataSet(entries, "")
    pieDataSet.valueTextColor = context.resources.getColor(R.color.grey)
    pieDataSet.setColors(intArrayOf(R.color.teal, R.color.pink), context) // Would be real cool to not use context here if possible.

    return PieData(pieDataSet)
}