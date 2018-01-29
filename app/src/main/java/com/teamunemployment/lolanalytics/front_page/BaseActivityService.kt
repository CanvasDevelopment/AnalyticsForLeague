package com.teamunemployment.lolanalytics.front_page

import com.teamunemployment.lolanalytics.data.model.DoubleWrapper
import com.teamunemployment.lolanalytics.data.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Josiah Kendall
 */
interface BaseActivityService {

    @GET("summoner/v1/FetchWinRate/{SummonerId}/{Role}") // todo make sure that this url is right
    fun getWinRateForRole(@Path("summonerId") summonerId : Long, @Path("role") role : String) : Call<Result<DoubleWrapper>>
}