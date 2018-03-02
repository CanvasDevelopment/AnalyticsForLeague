package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab

import com.teamunemployment.lolanalytics.data.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Josiah Kendall
 */
interface AnalysisService {

    @GET("/getStuff")
    fun getStatList() : Call<Result<ArrayList<String>>>

}