package com.teamunemployment.lolanalytics.front_page.champ_menu

import android.util.Log
import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.data.model.SimpleChamp
import com.teamunemployment.lolanalytics.data.model.SummonerRapidAccessObject
import com.teamunemployment.lolanalytics.data.remote.ChampService
import com.teamunemployment.lolanalytics.data.room.champ.ChampDao
import com.teamunemployment.lolanalytics.data.room.champ.SimpleChampDao
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import ru.gildor.coroutines.retrofit.await
import timber.log.Timber

/**
 * Created by Josiah Kendall
 */

class ChampMenuInteractor(private val champDao : ChampDao,
                          private val retrofitFactory: RetrofitFactory,
                          private val summonerRapidAccessObject : SummonerRapidAccessObject,
                          private val network: Network,
                          private val simpleChampDao: SimpleChampDao) {

    private var currentFilteredChamp: Champ? = null
    private val champService = retrofitFactory.produceRetrofitInterface(ChampService::class.java, network.getUrlForRegion("oce"))

    fun getChamps(champLoadResponse: ChampLoadResponse) {
        // fetch the champs from the database
        async {
            val localData = await { champDao.loadAllOurChamps(summonerRapidAccessObject.summonerId) }
            champLoadResponse.success(localData)
            val remoteData = await{champService.fetchAllChamps(summonerRapidAccessObject.summonerId).execute().body()!!}
            await { saveAllChamps(remoteData.data) }
            val simpleChampData = await{champService.fetchAllChamps().execute().body()!!}
            await { saveAllSimpleChamps(simpleChampData.data) }
            champLoadResponse.success(remoteData.data)
        }.onError {
//            presenter.showMessage("Offline - check you internet connection")
            // not doing anything here atm
        }
    }

    private fun saveAllChamps(champs : ArrayList<Champ>) {
        for (champ in champs) {
            champDao.createChamp(champ)
        }
    }

    private fun saveAllSimpleChamps(champs : ArrayList<SimpleChamp>) {
        for (simpleChamp in champs) {

            simpleChampDao.createChamp(simpleChamp)
        }
    }

    fun getCurrentSetChamp(): Champ? {
        return currentFilteredChamp
    }

    fun setCurrentChamp(champ: Champ?) {
        if (champ != null) {
            summonerRapidAccessObject.updateChampId(champ.key.toInt())
        } else {
            summonerRapidAccessObject.updateChampId(-1)
        }
        this.currentFilteredChamp = champ
    }

    fun setCurrentChamp(champKey : Int, callback: () -> Unit) {
        return
        if (champKey == -1) {
            this.currentFilteredChamp = null
        } else {
            async {
                val champ = await { champDao.loadChamp(champKey.toString(), summonerRapidAccessObject.summonerId) }
                currentFilteredChamp = champ
                callback()
            }.onError {
                // not doing anything here yet
            }
        }
    }

    fun clearCurrentChamp() {
        this.currentFilteredChamp = null
    }
}
