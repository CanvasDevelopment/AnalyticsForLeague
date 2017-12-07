package com.teamunemployment.lolanalytics.io.networking

/**
 * Created by Josiah Kendall
 */
class Regions {

    private val urlMap = HashMap<String, String>()

    init {
        urlMap.put("OCE", "https://lolanalyticsv3.appspot.com/_ah/api/myApi/v1/")
    }

    fun getRegionUrl(region : String ) : String {
        return urlMap[region] ?: throw IllegalStateException("You are using an incorrect region. Fix this")
    }
}