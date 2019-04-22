package com.teamunemployment.lolanalytics.data.remote

import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.data.model.SimpleChamp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ChampService {

    @GET("champ/v1/fetchAllChamps/{summonerId}")
    fun fetchAllChamps(@Path("summonerId") summonerId : String) : Call<Result<ArrayList<Champ>>>

    @GET("champ/v1/fetchAllChamps")
    fun fetchAllChamps() : Call<Result<ArrayList<SimpleChamp>>>


}