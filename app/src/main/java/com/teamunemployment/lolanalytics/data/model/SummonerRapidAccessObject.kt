package com.teamunemployment.lolanalytics.data.model

/**
 * @author Josiah Kendall
 */
class SummonerRapidAccessObject {
    var summonerId : Long = 0
    var role : Int = 0

    fun getRegion() : String{
        return "OCE"
    }
}