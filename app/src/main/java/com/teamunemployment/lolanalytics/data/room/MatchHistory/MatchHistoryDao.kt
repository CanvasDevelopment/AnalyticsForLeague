package com.teamunemployment.lolanalytics.data.room.MatchHistory

import android.arch.persistence.room.*
import com.teamunemployment.lolanalytics.data.model.ChampFrequency
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.MatchHistoryCardData
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.MatchIdentifier

@Dao
interface MatchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(matchHistoryCardData: MatchHistoryCardData)

    @Query("Select * FROM matchHistoryCardData where matchId = :matchId")
    fun loadMatchHistoryCardData(matchId : Long) : MatchHistoryCardData?

    @Query("Select * FROM matchHistoryCardData WHERE matchId = :matchId AND summonerId = :summonerId")
    fun loadMatchHistoryCardData(matchId : Long, summonerId : String) : MatchHistoryCardData?

    @Query("Select * FROM matchHistoryCardData where summonerId = :summonerId Order By MatchId ASC LIMIT 20 OFFSET :offset")
    fun loadTwentyMatchesForASummoner(summonerId : String, offset : Int) : List<MatchHistoryCardData>

    @Query("Select * FROM matchHistoryCardData where summonerId = :summonerId and champId = :champId Order By MatchId ASC LIMIT 20 OFFSET :offset")
    fun loadTwentyMatchesForASummoner(summonerId : String, offset : Int, champId : String) : List<MatchHistoryCardData>

    @Query("Select champId, count(*) as champCount from matchhistorycarddata where summonerId = :summonerId group by champId order by count(*) DESC")
    fun fetchMostPlayedChamps(summonerId : String) : List<ChampFrequency>

    @Update
    fun updateMatchHistoryCardData(matchHistoryCardData: MatchHistoryCardData)

    @Delete
    fun deleteMatchHistoryCardData(matchHistoryCardData: MatchHistoryCardData)

    //
    // ============ MATCH IDENTIFIER ==================
    //

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createMatchHistoryIdentifier(matchIdentifier: MatchIdentifier)

    @Query("SELECT * FROM MatchIdentifier WHERE matchId = :matchId AND summonerId = :summonerId")
    fun loadMatchIdentifier(matchId: Long, summonerId : String) : MatchIdentifier?

    @Query("SELECT * FROM MatchIdentifier Where summonerId = :summonerId Order By MatchId DESC LIMIT 20 OFFSET :offset")
    fun loadTwentyMatchIdentifiers(summonerId : String, offset: Int) : List<MatchIdentifier>

    @Query("SELECT * FROM MatchIdentifier Where summonerId = :summonerId and champKey = :champKey Order By MatchId DESC LIMIT 20 OFFSET :offset")
    fun loadTwentyMatchIdentifiers(summonerId : String, champKey : Int, offset: Int) : List<MatchIdentifier>

    @Query("SELECT * FROM MatchIdentifier Where summonerId = :summonerId and champKey = :champKey and role = :role and lane = :lane Order By MatchId DESC LIMIT 20 OFFSET :offset")
    fun loadTwentyMatchIdenfifiers(summonerId : String, champKey : Int, role : String, lane : String, offset: Int) : List<MatchIdentifier>

    @Query("SELECT * FROM MatchIdentifier Where summonerId = :summonerId and role = :role and lane = :lane Order By MatchId DESC LIMIT 20 OFFSET :offset")
    fun loadTwentyMatchIdenfifiers(summonerId : String, role : String, lane : String, offset: Int) : List<MatchIdentifier>

    @Query("SELECT * FROM MatchIdentifier Where summonerId = :summonerId and role = :role and lane = :lane and champKey = :champKey Order By MatchId DESC LIMIT 20 OFFSET :offset")
    fun loadTwentyMatchIdenfifiers(summonerId : String, role : String, lane : String, champKey : Int, offset: Int) : List<MatchIdentifier>


    @Update
    fun updateMatchIdentifier(matchIdentifier: MatchIdentifier)

    @Delete
    fun deleteMatchIdentifier(matchIdentifier: MatchIdentifier)
}