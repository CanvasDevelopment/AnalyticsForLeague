package com.teamunemployment.lolanalytics.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.teamunemployment.lolanalytics.data.room.summoner.Summoner
import com.teamunemployment.lolanalytics.data.room.summoner.SummonerDao

/**
 * Created by Josiah Kendall
 */
@Database(entities = arrayOf(Summoner::class), version = 2)
abstract class Database : RoomDatabase() {
    abstract fun summonerDao() : SummonerDao
}