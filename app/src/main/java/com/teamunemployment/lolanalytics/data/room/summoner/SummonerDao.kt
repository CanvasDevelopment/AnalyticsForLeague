package com.teamunemployment.lolanalytics.data.room.summoner

import android.arch.persistence.room.*

/**
 * Created by Josiah Kendall
 */
@Dao
interface SummonerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createSummoner(summoner: Summoner)

    @Query("Select * FROM summoner where summonerId = :summonerId")
    fun loadSummoner(summonerId : Long) : Summoner

    @Query("SELECT * FROM summoner")
    fun loadAllSummonersOnDevice() : List<Summoner>

    @Update
    fun updateSummoner(summoner: Summoner)

    @Delete
    fun deleteSummoner(summoner : Summoner) // note - user other daos to clean out associated information
}