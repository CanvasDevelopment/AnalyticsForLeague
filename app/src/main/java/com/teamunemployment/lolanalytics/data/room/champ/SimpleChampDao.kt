package com.teamunemployment.lolanalytics.data.room.champ

import android.arch.persistence.room.*
import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.data.model.SimpleChamp

@Dao
interface SimpleChampDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createChamp(simpleChamp: SimpleChamp)

    @Delete
    fun deleteChamp(simpleChamp : SimpleChamp)

    @Query("Select * FROM SimpleChamp where key = :champId")
    fun loadChamp(champId : String) : SimpleChamp?

}