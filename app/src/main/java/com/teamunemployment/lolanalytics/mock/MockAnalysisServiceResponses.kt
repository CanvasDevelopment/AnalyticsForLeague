package com.teamunemployment.lolanalytics.mock

/**
 * Created by Josiah Kendall
 */
class MockAnalysisServiceResponses {

    /**
     * Get the mock stat ids
     */
    fun getStatIds(id: Long): String {
        // return a string
        return "{" +
                "\"data\" : [" +
                "{" +
                    "\"type\": 2,"+
                    "\"card\" : \"testurl\"," +
                    "\"detail\" : \"detailsurl\"" +
                "}," +
                "{" +
                    "\"type\": 1,"+
                    "\"card\" : \"testurl\"," +
                    "\"detail\" : \"detailsurl\"" +
                "}" +
                "]}"
    }

    fun getFullStatCard() : String {
        return "{" +
                "\"data\" : [ " +
                "{\"enemyStatValue\" : 51.3," +
                "\"heroStatValue\" : 48.7 }," +

                "{\"enemyStatValue\" : 52.3," +
                "\"heroStatValue\" : 47.7 }," +

                "{\"enemyStatValue\" : 53.3," +
                "\"heroStatValue\" : 46.7 }" +
                "]}"
    }

    fun getHalfStatCard() : String {
        return "{" +
                "\"data\" : [ " +
                "{\"enemyStatValue\" : 51.3," +
                "\"heroStatValue\" : 48.7 }" +
                "]}"
    }

}