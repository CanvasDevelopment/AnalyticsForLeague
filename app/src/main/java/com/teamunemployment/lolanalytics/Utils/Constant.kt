package com.teamunemployment.lolanalytics.Utils

/**
 * Created by Josiah Kendall
 */

class Constant {

    object ViewType {
        val OFFSET_VIEW = 0
        val STANDARD_VIEW = 1
    }

    companion object {

        val CURRENT_SUMMONER_ID = "CURRENT_SUMMONER_ID"
        val ROLE_KEY = "role"
        val EARLY_GAME = 0
        val MID_GAME = 1
        val LATE_GAME = 2

        //Analysis card objects
    }

    /**
     * our different card types
     */
    object AnalysisCardType {
        val HALF_AD = 0
        val FULL_AD = 1
        val HALF_STAT = 2
        val FULL_STAT = 3
    }
}
