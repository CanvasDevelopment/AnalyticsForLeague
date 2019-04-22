package com.teamunemployment.lolanalytics.Utils

import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * @author Josiah Kendall
 */
class RegionHelper(private val sharedPreferences: SharedPreferences) {

    /**
     * Save a region against a summoner id. This will save the region reference for the given
     * summoner to the [SharedPreferences].
     *
     * @param region    The region to save for a summoner. Is the string representation of the region,
     *                  and must match the database
     */
    fun saveRegion(region : String, summonerId : String) : Boolean {
        sharedPreferences.edit()
                .putString(summonerId.toString(), region)
                .apply()

        return true
    }

    /**
     * Get the saved region for a summoner from the shared preferences
     *
     * @return The region value for a summoner that is stored in our shared preferences. Returns a
     * blank string if no value is saved.
     */
    fun getRegionForSummoner(summonerId : String) : String {
        return sharedPreferences.getString(summonerId.toString(), "")
    }
}