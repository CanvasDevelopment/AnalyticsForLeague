package com.teamunemployment.lolanalytics.data.model

import android.content.SharedPreferences
import com.teamunemployment.lolanalytics.Utils.Constant.Companion.CHAMP_KEY
import com.teamunemployment.lolanalytics.Utils.Constant.Companion.CURRENT_SUMMONER_ID
import com.teamunemployment.lolanalytics.Utils.Constant.Companion.ROLE_KEY
import org.koin.android.ext.android.inject

/**
 * @author Josiah Kendall
 *
 * Helper class for dealing with summoner shit that needs to be accessed rapidly but also have the
 * state persisted.
 */
class SummonerRapidAccessObject(private val sharedPreferences : SharedPreferences) {

    val summonerId : String = sharedPreferences.getString(CURRENT_SUMMONER_ID, "-1")
    var role : Int = sharedPreferences.getInt(ROLE_KEY, 1)
    var champKey : Int = sharedPreferences.getInt(CHAMP_KEY, -1)

    fun getRegion() : String {
        return "OCE"
    }

    fun updateRole(role : Int) {
        this.role = role
        sharedPreferences.edit()
                .putInt(ROLE_KEY, role)
                .apply()
    }

    fun updateChampId(champKey : Int) {
        this.champKey = champKey
        sharedPreferences
                .edit()
                .putInt(CHAMP_KEY, champKey)
                .apply()
    }

    fun saveCurrentSummonerId(summonerId : String) {

    }
}