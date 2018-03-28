package com.teamunemployment.lolanalytics.login.sign_in

import com.teamunemployment.lolanalytics.data.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Josiah Kendall
 */
interface LoginRemoteRepo {

    // Todo change this to include region at the base url for our endpoint (e.g na goes to na)
    @GET("summoner/v1/isRegistered/{summonerName}")
    fun getSummoner(@Path("summonerName") summonerName : String) : Call<Result<Boolean>>

    @GET("summoner/v1/register/{summonerName}")
    fun register(@Path("summonerName") summonerName : String) : Call<Result<SummonerDetails>>

    @GET("summoner/v1/sync/{summonerId}")
    fun beginSync(@Path("summonerId") summonerId: Long) : Call<Result<String>>
}