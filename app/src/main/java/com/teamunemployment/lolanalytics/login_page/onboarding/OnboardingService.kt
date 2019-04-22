package com.teamunemployment.lolanalytics.login_page.onboarding

import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.login_page.onboarding.model.SyncProgress
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Josiah Kendall
 */
interface OnboardingService {

    @GET("summoner/v1/sync/{summonerId}")
    fun syncUserMatchList(@Path("summonerId") summonerId : String) : Call<Result<String>>

    @GET("summoner/v1/syncProgress/{summonerId}")
    fun syncProgressReport(@Path("summonerId") summonerId : String) : Call<Result<SyncProgress>>

    @GET("summoner/v1/refineUserStats/{summonerId}")
    fun refineStats(@Path("summonerId") summonerId : String) : Call<Result<String>>
}