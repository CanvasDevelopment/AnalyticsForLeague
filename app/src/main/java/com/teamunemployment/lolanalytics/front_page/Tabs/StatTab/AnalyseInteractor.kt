package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab

import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatList
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import ru.gildor.coroutines.retrofit.await

import java.util.ArrayList

/**
 * Created by Josiah Kendall
 */

class AnalyseInteractor(private val retrofitFactory: RetrofitFactory,
                         private val network: Network){
    private lateinit var playerAnalysisRemoteRepo : PlayerAnalysisRemoteRepo

    fun loadStatList(role : Int, summonerId: Long, games: Int, presenter: AnalysePresenter) {

        async {

            val url = await{ network.getUrl(summonerId) }
            playerAnalysisRemoteRepo = retrofitFactory.produceRetrofitInterface(PlayerAnalysisRemoteRepo::class.java, url)

            val result = playerAnalysisRemoteRepo.fetchStatList(summonerId, role, games).await()
            result.cache()
            presenter.handleListResult(result)
        }
    }

    fun loadStatList(role: Int, summonerId: Long, games: Int, champId: Int, presenter: AnalysePresenter) {
        async {
            val url = await{network.getUrl(summonerId)}
            playerAnalysisRemoteRepo = retrofitFactory.produceRetrofitInterface(PlayerAnalysisRemoteRepo::class.java, url)
            val result = playerAnalysisRemoteRepo.fetchStatList(summonerId, role, champId, games).await()
            result.cache()
            presenter.handleListResult(result)
        }
    }

    fun loadIndividualStat(url : String, viewHolder: AnalyseTabContract.CardView, presenter: AnalysePresenter) {
        async {
            val result = playerAnalysisRemoteRepo.fetchIndividualStat(url).await()
            result.cache()
            presenter.handleCardResult(result, viewHolder)
        }
    }

    suspend fun<T> Result<T>.cache() : Boolean {

        if (resultCode == 200) {
            cacheData(data)
            TODO("Implement caching for player analysis interactor")
            return true
        }
        return false
    }

    private fun<T> cacheData(data : T) : Boolean {
        when(data) {
            is StatList -> {
                // do stat cache
                return true
            }
            is ArrayList<*> -> {
                // get object 0 to figure out what it is?
                return true
            }
        }

        return false
    }
}
