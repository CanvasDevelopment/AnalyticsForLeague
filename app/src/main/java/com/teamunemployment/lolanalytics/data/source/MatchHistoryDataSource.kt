package com.teamunemployment.lolanalytics.data.source

import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.data.model.ChampFrequency
import com.teamunemployment.lolanalytics.data.room.MatchHistory.MatchHistoryDao
import kotlinx.coroutines.experimental.delay

/**
 * This is a proxy class that handles the fetching the match history data.
 * Fetches data from the dao and from the server.
 *
 * The purpose of this class is to hide the async shit and extrapolate it from
 * any logic
 */
class MatchHistoryDataSource(private val matchHistoryDao : MatchHistoryDao) {

    /**
     * Fetch a list of champs from the database in order of most used.
     * This does not fetch from the server.
     *
     * @param summonerId The id of the summoner for whom we are fetching the
     *                   list of champs for.
     * @return a list of [ChampFrequency] items, representing the champions in order
     * of most used to least used.
     */
    fun fetchListOfChamps(summonerId : String) : List<ChampFrequency> {
        var listOfChamps = ArrayList<ChampFrequency>()

        async {
            await{Thread.sleep(1000)}
            println("fetching champs")
            listOfChamps = await{ArrayList(matchHistoryDao.fetchMostPlayedChamps(summonerId))}
        }
        println("returning champs")
        return listOfChamps
    }
}