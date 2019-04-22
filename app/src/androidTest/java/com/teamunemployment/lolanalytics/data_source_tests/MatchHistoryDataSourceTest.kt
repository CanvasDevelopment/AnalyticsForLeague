package com.teamunemployment.lolanalytics.data_source_tests

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import com.teamunemployment.lolanalytics.data.room.AppDatabase
import com.teamunemployment.lolanalytics.data.room.MatchHistory.MatchHistoryDao
import com.teamunemployment.lolanalytics.data.source.MatchHistoryDataSource
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model.MatchIdentifier
import org.junit.Test
import java.util.*

class MatchHistoryDataSourceTest {

    val context = InstrumentationRegistry.getTargetContext()

    private val database : AppDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
    private val random = Random()
    private val summonerId = random.nextLong().toString()
    private val matchHistoryDao = database.matchHistoryDao()
    private val matchHistoryDataSource = MatchHistoryDataSource(matchHistoryDao)


    @Test
    fun testThatWeCanLoadFromOurDatasourceCorrectly() {
        // save a bunch of champ

        for (i in 1..100) {
            val matchIdentifier = produceMatchIdentifier(i.toLong())
            matchHistoryDao.createMatchHistoryIdentifier(matchIdentifier)
        }

        val matches = matchHistoryDataSource.fetchListOfChamps(summonerId)
        assert(matches.size == 100)

    }

    private fun produceMatchIdentifier(matchId: Long) : MatchIdentifier {
        return MatchIdentifier(matchId,
                random.nextInt().toString(),
                random.nextInt().toString(),
                random.nextInt(),
                random.nextLong(),
                summonerId)
    }
}