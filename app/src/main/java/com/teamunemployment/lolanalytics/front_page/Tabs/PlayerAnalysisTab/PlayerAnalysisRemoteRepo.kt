package com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab

import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab.Model.StatList
import retrofit2.Call

/**
 * Created by Josiah Kendall
 */
interface PlayerAnalysisRemoteRepo {

    fun fetchStatList(role : Int) : Call<Result<StatList>>
}