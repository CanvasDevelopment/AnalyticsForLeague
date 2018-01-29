package com.teamunemployment.lolanalytics.login.onboarding

import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import retrofit2.Call
import retrofit2.Response

/**
 * Created by Josiah Kendall
 */
class OnboardingInteractor(private val retrofitFactory: RetrofitFactory, private val network: Network) {

    private lateinit var onboardingService : OnboardingService

    /**
     *
     */
    fun requestSync(onboardingPresenter: OnboardingPresenter, summonerId : Long) {

        async {
            val url = await {network.getUrl(summonerId) } // dont really like this stuff. using the db needs  to be async
            onboardingService  = retrofitFactory.produceRetrofitInterface(OnboardingService::class.java, url)

            val call : Call <Result<String>> = onboardingService.syncUser(summonerId)
            val response : Response<Result<String>> = await { call.execute() }

            val result : Result<String> = response.get()
            val code = result.resultCode

            onboardingPresenter.handleSyncResult(code == 200)
        }
    }



    private fun Response<Result<String>>.get() : Result<String> {
        if (body() != null) {
            return body()!!
        }

        return Result(404, "error")
    }
}