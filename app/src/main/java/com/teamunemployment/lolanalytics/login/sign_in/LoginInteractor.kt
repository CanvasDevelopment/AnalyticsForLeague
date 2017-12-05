package com.teamunemployment.lolanalytics.login.sign_in

import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.data.model.LongWrapper
import com.teamunemployment.lolanalytics.data.room.Database
import com.teamunemployment.lolanalytics.data.room.summoner.Summoner
import com.teamunemployment.lolanalytics.data.room.summoner.SummonerDao
import com.teamunemployment.lolanalytics.io.RealmExecutor
import retrofit2.Call
import retrofit2.Response

import javax.inject.Inject


/**
 * @author Josiah Kendall
 */

class LoginInteractor @Inject
constructor(private val loginRemoteRepo: LoginRemoteRepo, database: Database) {

    private val summonerDao = database.summonerDao()
    /**
     * Sends a request for the summoner to the server, and returns the response to the server. It
     * also saves the result to the db
     * @param summonerName The name of the summoner to search for
     * @param region The region we need to send the request to - todo
     * @param presenter
     */
    fun syncAUser(summonerName: String, region: String, presenter: LoginContract.Presenter) {
        async {
            // We want to check if a user exists. If they do, we want to log them in and trigger a sync.
            // This request
            val call : Call<LongWrapper> = loginRemoteRepo.getSummoner(summonerName)
            val response : Response<LongWrapper> =  await {call.execute()}
            val summonerId : LongWrapper = response.get()
            // handle the result
            presenter.handleSyncResult(response.code(), summonerId.value)
            if (response.code() == 200) {
                val summoner = Summoner(summonerId.value,"",0,"") // todo get all the data not just the summoner id
                summonerDao.createSummoner(summoner)
            }
        }.onError {
            // Should also probably log this, rather than returning to the user
            presenter.handleError(Throwable("An error occurred in the coroutine"))
        }
    }

    /**
     * Helper method to get the result from the [LongWrapper] and get rid of the null.
     */
    private fun Response<LongWrapper>.get() : LongWrapper {
        if (body() != null) {
            return body()!!
        }
        val result = LongWrapper()
        result.value = (-1).toLong()
        return result
    }
}
