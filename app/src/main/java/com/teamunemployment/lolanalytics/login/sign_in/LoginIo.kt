package com.teamunemployment.lolanalytics.login.sign_in

import com.teamunemployment.lolanalytics.data.model.Result
import retrofit2.Call
import retrofit2.Response

/**
 * Created by Josiah Kendall
 */
class LoginIo(private val loginRemoteRepo: LoginRemoteRepo) {

    fun registerUser(summonerName : String) : Result<SummonerDetails> {
        val call : Call<Result<SummonerDetails>> = loginRemoteRepo.register(summonerName)
        val response : Response<Result<SummonerDetails>> =  call.execute()
        // Process the data into a usable state
        return response.get()
    }

    /**
     * Helper method to getMatchIds the result from the [Result] and getMatchIds rid of the null.
     */
    private fun Response<Result<SummonerDetails>>.get() : Result<SummonerDetails> {
        if (body() != null) {
            return body()!!
        }

        return Result(-1, SummonerDetails(-1,-1,"",-1,-1,-1))
    }
}