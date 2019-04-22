package com.teamunemployment.lolanalytics.data.source

import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.data.remote.ChampService
import com.teamunemployment.lolanalytics.data.room.champ.ChampDao
import ru.gildor.coroutines.retrofit.await

class ChampSource(val champService: ChampService,
                  val champDao: ChampDao ) {

//    suspend fun produceChampList() : ArrayList<Champ> {
//        val champs = ArrayList(champDao.loadAllOurChamps())
//        if (champs.isEmpty()) {
//            // fetch champ list and cache it
//            champs.addAll(champService.fetchAllChamps().await().data)
//        }
//
//        return champs
//    }
}