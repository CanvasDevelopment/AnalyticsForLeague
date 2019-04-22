package com.teamunemployment.lolanalytics.login_page.sign_in

import android.content.SharedPreferences
import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.Utils.Constant
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.data.room.AppDatabase
import com.teamunemployment.lolanalytics.data.room.summoner.Summoner
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import retrofit2.Call
import retrofit2.Response



/**
 * @author Josiah Kendall
 */

class LoginInteractor
constructor(
        private val retrofitFactory: RetrofitFactory,
            database: AppDatabase,
            private val network: Network,
            private  val sharedPreferences: SharedPreferences) {

    private lateinit var loginIo: LoginIo
    private val summonerDao = database.summonerDao()
    private lateinit var loginRemoteRepo: LoginRemoteRepo


    fun isThereALoggedInUser() : String {
        return sharedPreferences.getString(Constant.CURRENT_SUMMONER_ID, "-1")
    }


    /**
     * Sends a request for the summoner to the server, and returns the response to the server. It
     * also saves the result to the db
     * @param summonerName The name of the summoner to search for
     * @param region The region we need to send the request to - todo
     * @param presenter
     */
    fun registerAUser(summonerName: String, region: String, presenter: LoginContract.Presenter) {
        val url = network.getUrlForRegion(region)
        loginRemoteRepo = retrofitFactory.produceRetrofitInterface(LoginRemoteRepo::class.java, url)
        loginIo = LoginIo(loginRemoteRepo)
        async {
            // Send call to server to register user // todo check if she exists first
            val call: Call<Result<SummonerDetails>> = loginRemoteRepo.register(summonerName)
            val response : Response<Result<SummonerDetails>> = await { call.execute() }
            // Process the data into a usable state
            val result: Result<SummonerDetails> = response.get()
            val summonerDetails = result.data
            // handle the result
            presenter.handleRegisterUserResult(result.resultCode, summonerDetails.id, region)

            // Cache the data (if we worked out ok)
            if (result.resultCode == 200) {
                sharedPreferences.edit().putString(Constant.CURRENT_SUMMONER_ID, result.data.id).apply()
                await {
                    result.data.cache(region)
                }
            }
        }.onError {
            // Should also probably log this, rather than returning to the user
            presenter.handleError(Throwable("Something went wrong - check your connection and try again"))
        }
    }

    private fun SummonerDetails.cache(region: String): SummonerDetails {

        val summoner = Summoner(id, name, summonerLevel, "", region) // todo getMatchIds the summoner devision
        summonerDao.createSummoner(summoner)

        return this
    }

    /**
     * Helper method to getMatchIds the result from the [Result] and getMatchIds rid of the null.
     */
    private fun Response<Result<SummonerDetails>>.get(): Result<SummonerDetails> {
        if (body() != null) {
            return body()!!
        }

        return Result(-1, SummonerDetails("-1", "-1", "", -1, -1, -1))
    }
}
