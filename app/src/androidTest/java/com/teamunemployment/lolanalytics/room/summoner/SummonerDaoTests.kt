package com.teamunemployment.lolanalytics.room.summoner


import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.teamunemployment.lolanalytics.data.room.AppDatabase
import com.teamunemployment.lolanalytics.data.room.summoner.Summoner
import com.teamunemployment.lolanalytics.data.room.summoner.SummonerDao
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Created by Josiah Kendall
 */
class SummonerDaoTests {

    constructor()
    private val random = Random()

    private lateinit var summonerDao : SummonerDao
    private lateinit var database : AppDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        summonerDao = database.summonerDao()
    }

    @Test
    fun Writeandreadsummoner() {
        val summoner = produceRandomSummoner()
        summonerDao.createSummoner(summoner)
        val retrieved = summonerDao.loadSummoner(summoner.summonerId)
        assert(retrieved!!.equals(summoner))
    }


    private fun produceRandomSummoner() :Summoner {
        return Summoner(
                random.nextLong().toString(),
                random.nextInt().toString(),
                random.nextInt(),
                random.nextInt().toString(),
                random.nextInt().toString())
    }

    // TODO move this to summoner BUT ONLY if we need it in production, else just use it in tests
    private fun Summoner.equals(summoner: Summoner) : Boolean {
        return summonerDivision == summoner.summonerDivision &&
                summonerId == summoner.summonerId &&
                summonerDivisionLevel == summoner.summonerDivisionLevel &&
                summonerName == summoner.summonerName
    }
}