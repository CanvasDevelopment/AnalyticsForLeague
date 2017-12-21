package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab

import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatCard
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatList
import retrofit2.Call

/**
 * Created by Josiah Kendall
 */
interface PlayerAnalysisRemoteRepo {

    fun fetchStatList(role : Int) : Call<Result<StatList>>
    fun fetchIndividualStat(url: String, summonerId: Long) : Call<Result<StatCard>>
}