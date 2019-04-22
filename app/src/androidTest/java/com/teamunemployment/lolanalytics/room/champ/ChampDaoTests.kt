package com.teamunemployment.lolanalytics.room.champ

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import com.teamunemployment.lolanalytics.data.model.Champ
import com.teamunemployment.lolanalytics.data.room.AppDatabase
import junit.framework.Assert.assertTrue
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList

class ChampDaoTests {

    val context = InstrumentationRegistry.getTargetContext()
    val database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
    val champDao = database.champDao()
    val random = Random()
    val summonerId = random.nextLong().toString()

    @Test
    fun testThatWeCanSaveAndLoadChamp() {
        val champ = createChamp()
        champDao.createChamp(champ)
        val returnedChamp = champDao.loadChamp(champ.id, summonerId)
        assertTrue( champ.blurb == returnedChamp!!.blurb)
        assertTrue( champ.id == returnedChamp.id)
        assertTrue(champ.key == returnedChamp.key)
        assertTrue(champ.name == returnedChamp.name)
        assertTrue(champ.title == returnedChamp.title)
        assertTrue(champ.games == returnedChamp.games)
        assertTrue(champ.summonerId == returnedChamp.summonerId)
    }

    @Test
    fun testThatWeCanUpdateChamp() {
        val champ = createChamp()
        champDao.createChamp(champ)
        val returnedChamp = champDao.loadChamp(champ.id, summonerId)
        assertTrue( champ.blurb == returnedChamp!!.blurb)
        assertTrue( champ.id == returnedChamp.id)
        assertTrue(champ.key == returnedChamp.key)
        assertTrue(champ.name == returnedChamp.name)
        assertTrue(champ.title == returnedChamp.title)
        assertTrue(champ.games == returnedChamp.games)
        assertTrue(champ.summonerId == returnedChamp.summonerId)

        val newChamp = createChamp(champ.id)
        newChamp._id = returnedChamp._id
        champDao.createChamp(newChamp)
        val newReturnedChamp = champDao.loadChamp(champ.id, summonerId)
        assertTrue( newChamp.blurb == newReturnedChamp!!.blurb)
        assertTrue( newChamp.id == newReturnedChamp.id)
        assertTrue(newChamp.key == newReturnedChamp.key)
        assertTrue(newChamp.name == newReturnedChamp.name)
        assertTrue(newChamp.title == newReturnedChamp.title)
        assertTrue(newChamp.games == newReturnedChamp.games)
        assertTrue(newChamp._id == newReturnedChamp._id)
        assertTrue(newChamp.summonerId == newReturnedChamp.summonerId)
    }

    @Test
    fun testThatWeCanLoadAListOfChamps() {
        val champs  = ArrayList(champDao.loadAllOurChamps(summonerId))
        assertTrue(champs.isEmpty())

        for (index in 1..5) {
            champDao.createChamp(createChamp())
        }

        champs.addAll(champDao.loadAllOurChamps(summonerId))
        assertTrue(champs.size == 5)
    }

    private fun createChamp(): Champ {
        return Champ(
                random.nextInt().toString(),
                random.nextInt().toString(),
                random.nextInt().toString(),
                random.nextInt().toString(),
                random.nextInt().toString(),
                random.nextInt().toString(),
                random.nextInt(),
                summonerId)
    }

    private fun createChamp(id : String): Champ {
        return Champ(
                random.nextInt().toString(),
                id,
                random.nextInt().toString(),
                random.nextInt().toString(),
                random.nextInt().toString(),
                random.nextInt().toString(),
                random.nextInt(),
                summonerId)
    }
}