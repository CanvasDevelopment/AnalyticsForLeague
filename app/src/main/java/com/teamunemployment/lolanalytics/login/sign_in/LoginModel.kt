package com.teamunemployment.lolanalytics.login.sign_in

import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.data.model.LongWrapper
import com.teamunemployment.lolanalytics.io.RealmExecutor
import retrofit2.Call
import retrofit2.Response

import javax.inject.Inject


/**
 * @author Josiah Kendall
 */

class LoginModel @Inject
constructor(private val loginRemoteRepo: LoginRemoteRepo, private val realmExecutor: RealmExecutor) {

    /**
     * Sends a request for the summoner to the server, and returns the response to the server. It also
     * @param summonerName The name of the summoner to search for
     * @param region The region we need to send the request to - todo
     * @param presenter
     */
    fun syncAUser(summonerName: String, region: String, presenter: LoginContract.Presenter){
        async {
            // We want to check if a user exists. If they do, we want to log them in and trigger a sync.
            // This request
            val call : Call<LongWrapper> = loginRemoteRepo.getSummoner(summonerName)
            val response : Response<LongWrapper> =  await {call.execute()}
            val summonerId : LongWrapper = response.get()
            // handle the result
            presenter.handleSyncResult(response.code(), summonerId.value)
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
