package com.teamunemployment.lolanalytics.data.model

/**
 * @author Josiah Kendall
 */
class SummonerRapidAccessObject {
    private var summonerId : Long = 0
    var role : Int = 0

    fun getRegion() : String{
        return "OCE"
    }

    fun summonerId() : Long {
        return summonerId
    }

    fun summonerId(summonerId : Long) {
        this.summonerId = summonerId
    }
}