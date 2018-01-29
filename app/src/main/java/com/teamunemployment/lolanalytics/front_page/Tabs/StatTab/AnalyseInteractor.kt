package com.teamunemployment.lolanalytics.front_page.Tabs.StatTab

import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.AnalysisData
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatList
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatCard
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import ru.gildor.coroutines.retrofit.await

import java.util.ArrayList

/**
 * Created by Josiah Kendall
 */

class AnalyseInteractor (private val retrofitFactory: RetrofitFactory,
                         private val network: Network){

    private lateinit var playerAnalysisRemoteRepo : PlayerAnalysisRemoteRepo

    fun loadStatTypes(role: Int, summonerId: Long, presenter: AnalysePresenter) {
        val url = network.getUrl(summonerId)
        playerAnalysisRemoteRepo = retrofitFactory.produceRetrofitInterface(PlayerAnalysisRemoteRepo::class.java, url)
        async {
            val result = playerAnalysisRemoteRepo.fetchStatList(role).await()
            result.cache()
            presenter.handleListResult(result)
        }
    }

    fun loadIndividualStat(url : String, summonerId: Long, viewHolder: AnalyseTabContract.CardView, presenter: AnalysePresenter) {
        async {
            val result = playerAnalysisRemoteRepo.fetchIndividualStat(url, summonerId).await()
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
            is StatCard -> {
                return true
            }
        }

        return false
    }

    fun RequestFilterList(role: Int, champId: Int, presenter: AnalyseTabContract.Presenter) {
        val datas = ArrayList<AnalysisData>()
        val analysisData = AnalysisData()
//        presenter.setStatList(removeMeAfterMocking())
    }

    fun RequestFilterList(role: Int, presenter: AnalysePresenter) {

        // send request to
//        presenter.setStatList(removeMeAfterMocking())
    }

    /**
     * TODO remove me
     */
    private fun removeMeAfterMocking(): ArrayList<AnalysisData> {
        val analysisData1 = AnalysisData()
        val analysisData2 = AnalysisData()
        val analysisData3 = AnalysisData()
        val analysisData4 = AnalysisData()
        val analysisData5 = AnalysisData()
        val analysisData6 = AnalysisData()

        analysisData1.recentChange = -3.1
        analysisData2.recentChange = -2.3
        analysisData3.recentChange = 3.3

        analysisData1.title = "Early Game"
        analysisData2.title = "Mid Game"
        analysisData3.title = "Late Game"

        analysisData1.heroPercentTotal = 45f
        analysisData2.heroPercentTotal = 44f
        analysisData3.heroPercentTotal = 47f

        analysisData1.enemyPercentTotal = 55f
        analysisData2.enemyPercentTotal = 56f
        analysisData3.enemyPercentTotal = 53f

        val analysisDatalist = ArrayList<AnalysisData>()
        analysisDatalist.add(analysisData1)
        analysisDatalist.add(analysisData2)
        analysisDatalist.add(analysisData3)
        return analysisDatalist

    }
}
