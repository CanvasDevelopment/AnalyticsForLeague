package com.teamunemployment.lolanalytics.LoginTests

import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.room.Database
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import com.teamunemployment.lolanalytics.login.sign_in.LoginInteractor
import com.teamunemployment.lolanalytics.login.sign_in.LoginRemoteRepo
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Created by Josiah Kendall
 */
class LoginInteracterTests() {


    @Test
    fun `Test Suspend Function`() {
//        val retrofitFactory = mock(RetrofitFactory::class.java)
//        `when`(retrofitFactory.produceRetrofitInterface(LoginRemoteRepo::class.java, ""))
//        val interactor = LoginInteractor(retrofitFactory,mock(Database::class.java),mock(Network::class.java))
//        `when`()
//        interactor.doIoStuff("john", )
    }
}