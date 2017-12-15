package com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab

import co.metalab.asyncawait.async

import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.data.room.Database
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.Model.StatList
import com.teamunemployment.lolanalytics.front_page.Tabs.StatTab.PlayerAnalysisRemoteRepo
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory

import javax.inject.Inject

import retrofit2.Call


/**
 * @author Josiah Kendall
 *
 * The persisitance interactor to load value from the local and remote storage for the analysis tab.
 */

class PlayerAnalysisPersistanceInteracter @Inject
constructor(private val retrofitFactory: RetrofitFactory, private val database: Database, private val network: Network) {

    private lateinit var playerAnalysisRemoteRepo : PlayerAnalysisRemoteRepo

    /**
     * Load the list of stats that we want to display.
     * @param role The Role we want the stats for.
     * @param summonerId The summoner we want the stats for.
     */
    fun loadStatTypes(role: Int, summonerId: Long, presenter: PlayerAnalysisPresenter) {

        val url = network.getUrl(summonerId)
        playerAnalysisRemoteRepo = retrofitFactory.produceRetrofitInterface(PlayerAnalysisRemoteRepo::class.java, url)
        async {
            val result = playerAnalysisRemoteRepo.fetchStatList(role).await()
            val cache = result.cache()
            presenter.handleListResult(result)
        }
    }

    fun loadIndividualStat(url : String) {
        async {
            val result = playerAnalysisRemoteRepo.fetchIndividualStat(url, summonerId).await()
        }
    }

    private suspend fun Call<Result<StatList>>.await() : Result<StatList> {
        val result = execute()
        if (result.isSuccessful && result.body() != null) {
            return result.body()!!
        }

        return Result(500, StatList())
    }

    suspend fun Result<StatList>.cache() : Boolean {

        if (resultCode == 200) {
            // save
            TODO("Implement caching for player analysis interactor")
            return true
        }
        return false
    }

}
