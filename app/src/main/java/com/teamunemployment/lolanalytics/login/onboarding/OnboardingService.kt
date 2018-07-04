package com.teamunemployment.lolanalytics.login.onboarding

import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.login.onboarding.model.SyncProgress
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Named

/**
 * Created by Josiah Kendall
 */
interface OnboardingService {

    @GET("summoner/v1/sync/{summonerId}")
    fun syncUserMatchList(@Path("summonerId") summonerId : Long) : Call<Result<String>>

    @GET("summoner/v1/syncProgress/{summonerId}")
    fun syncProgressReport(@Path("summonerId") summonerId: Long) : Call<Result<SyncProgress>>

    @GET("summoner/v1/refineUserStats/{summonerId}")
    fun refineStats(@Path("summonerId") summonerId: Long) : Call<Result<String>>
}