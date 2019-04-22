package com.teamunemployment.lolanalytics.data.room.champ

import android.arch.persistence.room.*
import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.data.room.summoner.Summoner

@Dao
interface ChampDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createChamp(champ: Champ)

    @Delete
    fun deleteChamp(champ : Champ)

    @Query("Select * FROM champ where key = :champId and summonerId = :summonerId")
    fun loadChamp(champId : String, summonerId : String) : Champ?

    @Query("SELECT * FROM champ where summonerId = :summonerId")
    fun loadAllOurChamps(summonerId : String) : List<Champ>

}