package com.teamunemployment.lolanalytics.login

import co.metalab.asyncawait.async
import com.teamunemployment.lolanalytics.Data.model.LongWrapper
import com.teamunemployment.lolanalytics.io.RealmExecutor

import javax.inject.Inject


/**
 * @author Josiah Kendall
 */

class LoginModel @Inject
constructor(private val loginRestService: LoginRestService, private val realmExecutor: RealmExecutor) {

    /**
     * Login a user.
     * @param summonerName The name of the summoner to search for
     */
    fun login(summonerName: String, region: String, presenter: LoginContract.Presenter){
        async {
            val result : LongWrapper = await { loginRestService.doesUserExist(summonerName, region) }

            if (result.data != 200.toLong()) {
                presenter.handleLoginResult(result.data)
            } else {
                // save user to database
                val syncResult = await { loginRestService.beginSync(summonerName, region) }
                presenter.handleLoginResult(syncResult.data)
            }
        }
    }
}
