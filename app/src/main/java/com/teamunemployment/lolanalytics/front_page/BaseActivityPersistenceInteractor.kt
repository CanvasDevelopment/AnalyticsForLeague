package com.teamunemployment.lolanalytics.front_page

import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.io.RealmExecutor
import com.teamunemployment.lolanalytics.data.model.DoubleWrapper
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.data.room.Database
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory

import javax.inject.Inject

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response


/**
 * @author Josiah Kendall
 * Interactor for fetching
 */

class BaseActivityPersistenceInteractor @Inject
constructor(private val retrofitFactory: RetrofitFactory, // Our repositories.
            private val database : Database,
            private val network : Network) {

    private lateinit var baseActivityService: BaseActivityService

    fun fetchWinRateForRole(summonerId : Long, role: String, region : String, presenter: BaseActivityContract.Presenter) {
        async {
            val url = network.getUrl(region)
            baseActivityService = retrofitFactory.produceRetrofitInterface(BaseActivityService::class.java, url)
            val call : Call<Result<DoubleWrapper>> = baseActivityService.getWinRateForRole(summonerId, role)
            val response : Response<Result<DoubleWrapper>> = await { call.execute() }

            val result : Result<DoubleWrapper> = response.get()
            val code = result.resultCode // do something here?
            presenter.onWinRateLoaded(result.data.data)
        }
    }

    private fun Response<Result<DoubleWrapper>>.get() : Result<DoubleWrapper> {
        if (body() != null) {
            return body()!!
        }

        return Result(404, DoubleWrapper())
    }
}
