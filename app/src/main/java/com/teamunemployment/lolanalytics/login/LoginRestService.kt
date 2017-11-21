package com.teamunemployment.lolanalytics.login

import com.teamunemployment.lolanalytics.Data.model.LongWrapper

/**
 * Created by Josiah Kendall
 */
interface LoginRestService {

    fun doesUserExist(userName : String, region : String) : LongWrapper
    fun beginSync(userName: String, region: String) : LongWrapper
}