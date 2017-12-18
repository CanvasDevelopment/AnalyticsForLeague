package com.teamunemployment.lolanalytics.Utils

import android.content.Context
import android.net.ConnectivityManager
import com.teamunemployment.lolanalytics.BuildConfig
import com.teamunemployment.lolanalytics.data.room.Database
import com.teamunemployment.lolanalytics.data.room.summoner.SummonerDao

/**
 * @author Josiah Kendall
 */

class Network(private val database: Database) {

    private val summonerDao = database.summonerDao()
    private val localDebugUrl = "http://192.168.1.3:8080/_ah/api/"

    fun isConnectingToInternet(context: Context): Boolean {

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    /**
     * Get the url for the given region. Note that if you are currently running a debug build, it
     * will return
     */
    fun getUrl(region : String ) : String {
        if (BuildConfig.DEBUG) {
            return localDebugUrl
        }
        TODO("Write and test")
        return ""
    }

    fun getUrl(summonerId : Long) : String {
        // Todo make this logic async
        val summoner = summonerDao.loadSummoner(summonerId)
        return getUrl(summoner.region)
    }
}