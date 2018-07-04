package com.teamunemployment.lolanalytics.data.model

import android.content.SharedPreferences
import com.teamunemployment.lolanalytics.Utils.Constant.CURRENT_SUMMONER_ID
import com.teamunemployment.lolanalytics.Utils.Constant.ROLE_KEY
import org.koin.android.ext.android.inject

/**
 * @author Josiah Kendall
 *
 * Helper class for dealing with summoner shit that needs to be accessed rapidly but also have the
 * state persisted.
 */
class SummonerRapidAccessObject(private val sharedPreferences: SharedPreferences) {

    val summonerId : Long = sharedPreferences.getLong(CURRENT_SUMMONER_ID, -1)
    val role : Int = sharedPreferences.getInt(ROLE_KEY, 0)

    fun getRegion() : String{
        return "OCE"
    }

    fun updateRole(role : Int) {
        sharedPreferences.edit()
                .putInt(ROLE_KEY, role)
                .apply()
    }
}