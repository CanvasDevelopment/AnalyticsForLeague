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
        val CHAMP_KEY = "champ_key"
        val EARLY_GAME = 0
        val MID_GAME = 1
        val LATE_GAME = 2

        //Analysis cardUrl objects
    }

    /**
     * Our different cardUrl types
     */
    object AnalysisCardType {

        /**
         * An add that covers half the screen.
         */
        val HALF_AD = 0

        /**
         * An add that covers the full width of the screen
         */
        val FULL_AD = 1

        /**
         * A stat that represents the total for that user over the whole game. This is represented
         * by one graph. Kills is an example of this.
         */
        val HALF_STAT = 2

        /**
         * A stat that has an early game, mid game and late game stat. This meas that it is broken
         * down into three separate graphs. An Example is Creeps Per Minute
         */
        val FULL_STAT = 3
    }
    object DetailsPage {
        val detailsUrlKey = "detailsUrl"
    }

}
