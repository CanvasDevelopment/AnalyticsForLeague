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
                    "\"type\": 3,"+
                    "\"cardUrl\" : \"testurl\"," +
                    "\"detailUrl\" : \"detailsurl\"," +
                    "\"title\" : \"Creeps Per Minute\"" +
                "}," +
                "{" +
                    "\"type\": 3,"+
                    "\"cardUrl\" : \"testurl\"," +
                    "\"detailUrl\" : \"detailsurl\"," +
                    "\"title\" : \"Gold Per Minute\"" +
                "}" +
                "]}"
    }

    fun getStatDetails() : String {
        return "{\n" +
                "\"data\" : {\n" +
                "\t\"historical\" : [\n" +
                "\t\t[45, 56, 58, 58, 51, 64, 63],\n" +
                "\t\t[51, 53, 59, 68, 61, 74, 73],\n" +
                "\t\t[45, 56, 58, 58, 51, 64, 63]\n" +
                "\t],\n" +
                "\t\"vsOpponent\" : [{\n" +
                "\t\t\t\"heroStatValue\" : \"57.4\",\n" +
                "\t\t\t\"enemyStatValue\" : \"44.5\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"heroStatValue\" : 57.4,\n" +
                "\t\t\t\"enemyStatValue\" : 44.5\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"heroStatValue\" : 57.4,\n" +
                "\t\t\t\"enemyStatValue\" : 44.5\n" +
                "\t\t}\n" +
                "\t\t],\n" +
                "\t\"vsDivision\" : [\n" +
                "\t\t{\n" +
                "\t\t\t\"heroStatValue\" : 57.4,\n" +
                "\t\t\t\"enemyStatValue\" : 44.5\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"heroStatValue\" : 57.4,\n" +
                "\t\t\t\"enemyStatValue\" : 44.5\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"heroStatValue\" : 57.4,\n" +
                "\t\t\t\"enemyStatValue\" : 44.5\n" +
                "\t\t}\n" +
                "\t\t],\n" +
                "\n" +
                "\t\"divisionName\" : \"Silver 3\"\n" +
                "}\n" +
                "}"
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