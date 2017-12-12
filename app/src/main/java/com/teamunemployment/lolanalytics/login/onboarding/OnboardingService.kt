package com.teamunemployment.lolanalytics.login.onboarding

import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.data.model.Result
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
    fun syncUser(@Path("summonerId") summonerId : Long) : Call<Result<String>>
}