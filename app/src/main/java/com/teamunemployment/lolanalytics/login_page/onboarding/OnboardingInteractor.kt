package com.teamunemployment.lolanalytics.login_page.onboarding

import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import com.teamunemployment.lolanalytics.login_page.onboarding.model.SyncProgress
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
    fun requestSync(onboardingPresenter: OnboardingPresenter, summonerId : String) {

        async {
            val url = await{network.getUrl(summonerId)}
            onboardingService = retrofitFactory.produceRetrofitInterface(OnboardingService::class.java, url)

            val call : Call <Result<String>> = onboardingService.syncUserMatchList(summonerId)
            val response : Response<Result<String>> = await { call.execute() }

            val result : Result<String> = response.get()
            val code = result.resultCode

            if (code == 200) {
                // progress to stage 2
                await {
                    var finished = false
                    while(!finished) {
                        Thread.sleep(500)
                        val updateSyncProgressCall : Call <Result<SyncProgress>> = onboardingService.syncProgressReport(summonerId)
                        val response : Response<Result<SyncProgress>> = updateSyncProgressCall.execute()
                        val result : Result<SyncProgress> = response.getSyncProgress()
                        if (result.resultCode == 200) {
                            // handle sync progress update
                            finished = onboardingPresenter.handleSyncProgressUpdate(result.data)
                            if (finished) {
                                // request processing
                                val refineProgressCall : Call <Result<String>> = onboardingService.refineStats(summonerId)
                                val response : Response<Result<String>> = refineProgressCall.execute()
                                val result : Result<String> = response.get()
                                if (result.resultCode == 200) {
                                    // launch new screen
                                    onboardingPresenter.launchHome()
                                }
                            }
                        }

                    }
                }
            }

            onboardingPresenter.handleSyncResult(code == 200)
        }.onError {
            onboardingPresenter.showMessage("Offline - check you internet connection")
        }
    }

    private fun Response<Result<String>>.get() : Result<String> {
        if (body() != null) {
            return body()!!
        }

        return Result(404, "error")
    }

    private fun Response<Result<SyncProgress>>.getSyncProgress() : Result<SyncProgress> {
        if (body() != null) {
            return body()!!
        }

        return Result(404, SyncProgress(100, 0))
    }
}