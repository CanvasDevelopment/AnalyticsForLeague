package com.teamunemployment.lolanalytics.room.match

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import com.teamunemployment.lolanalytics.data.room.AppDatabase
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.HeadToHeadStat
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.MatchHistoryCardData
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.MatchIdentifier
import junit.framework.Assert.assertTrue
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList

class MatchDaoTests {
    val context = InstrumentationRegistry.getTargetContext()

    private val database : AppDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
    private val random = Random()
    private val summonerId = random.nextLong().toString()
    private val matchHistoryDao = database.matchHistoryDao()

    @Test
    fun testThatWeCanSaveAndLoadMatchHistoryCardData() {
        val matchId = random.nextLong()
        val matchHistoryCardData = produceMatchHistoryCardData(matchId)
        matchHistoryDao.insert(matchHistoryCardData)
        val returned = matchHistoryDao.loadMatchHistoryCardData(matchId,summonerId)
        assertTrue(returned == matchHistoryCardData)
    }

    @Test
    fun testThatWeCanSaveAndLoadMatchIdentifier() {
        val matchId = random.nextLong()
        val matchIdentifier = produceMatchIdentifier(matchId)
        matchHistoryDao.createMatchHistoryIdentifier(matchIdentifier)
        val returnValue = matchHistoryDao.loadMatchIdentifier(matchId, summonerId)
        assertTrue(returnValue != matchIdentifier)
        matchIdentifier.id = returnValue!!.id
//        assertTrue(returnValue == matchIdentifier)
    }

    @Test
    fun testThatWeCanSaveAndLoadMatchIdentifierWithRoleAndLane() {
        val matchId = random.nextLong()
        val lane = "TOP"
        val role = "SOLO"
        val matchIdentifier = produceMatchIdentifier(matchId, role, lane)
        val matchIdentifier2 = produceMatchIdentifier(matchId)

        matchHistoryDao.createMatchHistoryIdentifier(matchIdentifier)
        matchHistoryDao.createMatchHistoryIdentifier(matchIdentifier2)

        val values = matchHistoryDao.loadTwentyMatchIdenfifiers(summonerId, role, lane, 0)
        assertTrue(values.size == 1)
        matchIdentifier.id = values[0].id
        assertTrue(values[0] == matchIdentifier)
    }

    @Test
    fun testThatWeCanSaveAndLoadMatchIdentifierWithChampAndRoleAndLane() {
        val matchId = random.nextLong()
        val lane = "TOP"
        val role = "SOLO"
        val champ = random.nextInt()
        val matchIdentifier = produceMatchIdentifier(matchId, role, lane, champ)
        val matchIdentifier2 = produceMatchIdentifier(matchId)

        matchHistoryDao.createMatchHistoryIdentifier(matchIdentifier)
        matchHistoryDao.createMatchHistoryIdentifier(matchIdentifier2)

        val values = matchHistoryDao.loadTwentyMatchIdenfifiers(summonerId, role, lane, champ, 0)
        assertTrue(values.size == 1)
        matchIdentifier.id = values[0].id
        assertTrue(values[0] == matchIdentifier)


    }

    @Test
    fun testThatWeCanLoadMostUsedChamps() {
        val champOneId = 5
        val champTwoId = 6

        val matchOne = produceMatchHistoryCardData(random.nextLong(), champOneId)
        val matchTwo = produceMatchHistoryCardData(random.nextLong(), champTwoId)
        val matchthree = produceMatchHistoryCardData(random.nextLong(), champTwoId)

        matchHistoryDao.insert(matchOne)
        matchHistoryDao.insert(matchTwo)
        matchHistoryDao.insert(matchthree)

        val list = matchHistoryDao.fetchMostPlayedChamps(summonerId)
        assertTrue(list.size == 2)
        assertTrue(list[0].champId == champTwoId)
        assertTrue(list[0].champCount == 2)
        assertTrue(list[1].champId == champOneId)
        assertTrue(list[1].champCount == 1)
    }

    private fun produceMatchHistoryCardData(matchId : Long) : MatchHistoryCardData  {
        return MatchHistoryCardData(
            matchId,random.nextLong(),
                random.nextLong(),
                produceRandomHeadToHeadStat(),
                produceRandomHeadToHeadStat(),
                produceRandomHeadToHeadStat(),
                random.nextBoolean(),
                random.nextInt().toString(),
                summonerId
        )
    }

    private fun produceMatchHistoryCardData(matchId : Long, champId : Int) : MatchHistoryCardData  {
        return MatchHistoryCardData(
                matchId,
                champId.toLong(),
                random.nextLong(),
                produceRandomHeadToHeadStat(),
                produceRandomHeadToHeadStat(),
                produceRandomHeadToHeadStat(),
                random.nextBoolean(),
                random.nextInt().toString(),
                summonerId
        )
    }

    private fun produceMatchIdentifier(matchId: Long) : MatchIdentifier {
        return MatchIdentifier(matchId,
                random.nextInt().toString(),
                random.nextInt().toString(),
                random.nextInt(),
                random.nextLong(),
                summonerId)
    }

    private fun produceMatchIdentifier(matchId: Long, role : String, lane : String) : MatchIdentifier {
        return MatchIdentifier(matchId,
                role,
                lane,
                random.nextInt(),
                random.nextLong(),
                summonerId)
    }

    private fun produceMatchIdentifier(matchId: Long, role : String, lane : String, champ : Int) : MatchIdentifier {
        return MatchIdentifier(matchId,
                role,
                lane,
                champ,
                random.nextLong(), // timestamp
                summonerId)
    }

    private fun produceRandomHeadToHeadStat() : HeadToHeadStat{
        return HeadToHeadStat(random.nextFloat(), random.nextFloat())
    }
}