package com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab

import android.content.Context
import co.metalab.asyncawait.async

import com.github.mikephil.charting.data.Entry
import com.teamunemployment.lolanalytics.io.RESTApiExecutor
import com.teamunemployment.lolanalytics.io.RealmExecutor
import com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab.Model.StatCollection
import com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab.Model.StatDefinitionWrapper
import com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab.Model.StatSummary
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.data.room.Database
import com.teamunemployment.lolanalytics.front_page.Tabs.PlayerAnalysisTab.Model.StatList
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import com.teamunemployment.lolanalytics.login.sign_in.SummonerDetails

import java.util.ArrayList
import java.util.Random

import javax.inject.Inject

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import retrofit2.Call
import kotlin.coroutines.experimental.suspendCoroutine


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
