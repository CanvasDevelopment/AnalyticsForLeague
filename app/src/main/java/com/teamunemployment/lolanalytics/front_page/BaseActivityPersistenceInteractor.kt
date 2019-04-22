package com.teamunemployment.lolanalytics.front_page

import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.model.DoubleWrapper
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.data.room.AppDatabase
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory


import retrofit2.Call
import retrofit2.Response


/**
 * @author Josiah Kendall
 * Interactor for fetching
 */

class BaseActivityPersistenceInteractor
constructor(private val retrofitFactory : RetrofitFactory, // Our repositories.
            private val database : AppDatabase,
            private val network : Network) {

    private lateinit var baseActivityService: BaseActivityService

    fun fetchWinRateForRole(summonerId : String, role : String, region : String, presenter: BaseActivityContract.Presenter) {
        async {
            val url = network.getUrlForRegion(region)
            baseActivityService = retrofitFactory.produceRetrofitInterface(BaseActivityService::class.java, url)
            val call : Call<Result<DoubleWrapper>> = baseActivityService.getWinRateForRole(summonerId, role)
            val response : Response<Result<DoubleWrapper>> = await { call.execute() }

            val result : Result<DoubleWrapper> = response.get()
            val code = result.resultCode // do something here?
            presenter.onWinRateLoaded(result.data.data)
        }.onError {
            presenter.showMessage("Offline - check you internet connection")

        }
    }

    private fun Response<Result<DoubleWrapper>>.get() : Result<DoubleWrapper> {
        if (body() != null) {
            return body()!!
        }

        return Result(404, DoubleWrapper())
    }
}
