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
                "[" +
                "{" +
                    "\"type\": 2,"+
                    "\"card\" : \"testurl\"," +
                    "\"detail\" : \"detailsurl\"" +
                "}," +
                "{" +
                    "\"type\": 2,"+
                    "\"card\" : \"testurl\"," +
                    "\"detail\" : \"detailsurl\"" +
                "}" +
                "]}"
    }

}