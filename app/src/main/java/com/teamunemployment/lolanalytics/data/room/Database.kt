package com.teamunemployment.lolanalytics.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.teamunemployment.lolanalytics.data.room.MatchHistory.MatchHistoryDao
import com.teamunemployment.lolanalytics.data.room.summoner.Summoner
import com.teamunemployment.lolanalytics.data.room.summoner.SummonerDao
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.MatchIdentifier

/**
 * Created by Josiah Kendall
 */
//@Database(entities = {Summoner::class.java, MatchIdentifier::class.java}, version = 1)
//abstract class Database : RoomDatabase() {
//    abstract fun summonerDao() : SummonerDao
//    abstract fun matchHistoryDao() : MatchHistoryDao
//}