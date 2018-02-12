package com.teamunemployment.lolanalytics.Utils

import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Cards.MatchHistoryGameStageData
import com.teamunemployment.lolanalytics.front_page.Tabs.MatchHistoryTab.Model.ArrayListWrapper
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
            -1,"",-1, MatchHistoryGameStageData(0f,0f),MatchHistoryGameStageData(0f,0f),MatchHistoryGameStageData(0f,0f)))
}