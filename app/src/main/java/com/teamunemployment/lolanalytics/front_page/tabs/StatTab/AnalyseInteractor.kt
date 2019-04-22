package com.teamunemployment.lolanalytics.front_page.tabs.StatTab

import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.Model.*
import com.teamunemployment.lolanalytics.front_page.tabs.StatTab.recycler.StatCardView
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import retrofit2.Call
import ru.gildor.coroutines.retrofit.await

import java.util.ArrayList

/**
 * Created by Josiah Kendall
 */

class AnalyseInteractor(private val retrofitFactory: RetrofitFactory,
                        private val network: Network) {

    private lateinit var playerAnalysisRemoteRepo: PlayerAnalysisRemoteRepo

    fun loadIndividualStat(url: String, viewHolder : StatCardView, presenter : AnalysePresenter) {
        async {
            val result : Result<ArrayList<HeadToHeadStat>> = await{playerAnalysisRemoteRepo.fetchIndividualStat(url).execute().body()!!}
            result.cache()
            presenter.handleCardResult(result.data, viewHolder)
        }.onError {
            presenter.showMessage("Offline - check you internet connection")
        }
    }

    /**
     * Cache our data that we have fetched.
     */
    private fun <T> Result<T>.cache(): Boolean {

        if (resultCode == 200) {
            cacheData(data)
            return true
        }
        return false
    }

    private fun <T> cacheData(data: T): Boolean {
        when (data) {
            is StatList -> {
                // do stat cache
                // todo
                return true
            }
            is FullStatCard -> {
                // todo
                return true
            }
        }

        return false
    }

    fun loadStatList(role : String, champId : Int, presenter : AnalysePresenter, region : String) {

    }

    fun loadStatList(role : String, presenter : AnalysePresenter, region: String, summonerId : String) {

        async {
            val url = network.getUrlForRegion(region)
            playerAnalysisRemoteRepo = retrofitFactory.produceRetrofitInterface(PlayerAnalysisRemoteRepo::class.java, url)
            val call : Call<Result<ArrayList<StatSummary>>> = playerAnalysisRemoteRepo.fetchStatList(summonerId,role,20)

            val result : Result<ArrayList<StatSummary>> = await { call.execute().body()!! }
            val code = result.resultCode // do something here?
            if (code in 200..299) {
                presenter.setStatList(result.data)
            }
        }.onError {
            presenter.showMessage("Offline - check you internet connection")
        }


        // send request
        // send request to
//        presenter.setStatList(removeMeAfterMocking())
    }
}
