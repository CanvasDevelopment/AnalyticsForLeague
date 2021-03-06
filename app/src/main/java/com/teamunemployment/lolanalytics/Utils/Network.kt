package com.teamunemployment.lolanalytics.Utils

import android.content.Context
import android.net.ConnectivityManager
import com.teamunemployment.lolanalytics.BuildConfig
import com.teamunemployment.lolanalytics.data.room.AppDatabase

/**
 * @author Josiah Kendall
 */

class Network(private val database: AppDatabase) {

    private val summonerDao = database.summonerDao()
    private val localDebugUrl = "http://192.168.21.77:8080"

    fun isConnectingToInternet(context : Context) : Boolean {

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    /**
     * Get the url for the given region. Note that if you are currently running a debug build, it
     * will return
     */
    fun getUrlForRegion(region: String) : String {
        if (BuildConfig.DEBUG) {
            return "$localDebugUrl/_ah/api/"
        }
        TODO("Write and test")
        // get region.
        // build url to gae based on region.
        // return url
        return ""
    }

    fun getRawUrl(region: String) : String {
        if (BuildConfig.DEBUG) {
            return localDebugUrl
        }
        TODO("Write and test")
        // get region.
        // build url to gae based on region.
        // return url
        return ""
    }

    fun getUrl(summonerId : String) : String {
        // Todo make this logic async
        val summoner = summonerDao.loadSummoner(summonerId)
        if (summoner != null) {
            return getUrlForRegion(summoner.region)
        }

        return ""
    }
}
