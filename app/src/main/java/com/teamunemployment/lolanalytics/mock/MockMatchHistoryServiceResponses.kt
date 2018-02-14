package com.teamunemployment.lolanalytics.mock

/**
 * @author Josiah Kendall
 *
 * Just a class to store mock responses for testing the app without needing a server.
 */
class MockMatchHistoryServiceResponses {

    fun getMatchList() : String {
        return "{\"data\":[\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\"],\"someOtherShit\" : \"xcvvvfderre\"}"
    }

    fun getMatchSummary(id: Long): String {
        // return a string
        return "{\"data\" :{" +
                "\"matchId\" : 1,"+
                "\"champId\" : 1," +
                "\"champName\" : \"vi\"," +
                "\"enemyChampId\" : 2," +
                "\"earlyGame\" : {\"enemyStatValue\": 51.234,\"heroStatValue\": 49.234}," +
                "\"midGame\" : {\"enemyStatValue\": 51.234,\"heroStatValue\": 49.234}," +
                "\"lateGame\" : {\"enemyStatValue\": 51.234,\"heroStatValue\": 49.234}" +
                "}}"
    }

    fun getMatchDetails() : String {
        return "{\n" +
                "\t\"data\": {\n" +
                "\t\t\"kda\" : [{\n" +
                "\t\t\t\t\"enemyStatValue\": 4.0,\n" +
                "\t\t\t\t\"heroStatValue\": 5.0\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"enemyStatValue\": 3.0,\n" +
                "\t\t\t\t\"heroStatValue\": 5.0\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"enemyStatValue\": 4.0,\n" +
                "\t\t\t\t\"heroStatValue\": 9.0\n" +
                "}]," +
                "\t\t\"Creeps\": [{\n" +
                "\t\t\t\t\"enemyStatValue\": 45.4,\n" +
                "\t\t\t\t\"heroStatValue\": 52.3\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"enemyStatValue\": 45.4,\n" +
                "\t\t\t\t\"heroStatValue\": 52.3\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"enemyStatValue\": 45.4,\n" +
                "\t\t\t\t\"heroStatValue\": 52.3\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\n" +
                "\t\t\"damageDealt\": [{\n" +
                "\t\t\t\t\"enemyStatValue\": 45.4,\n" +
                "\t\t\t\t\"heroStatValue\": 52.3\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"enemyStatValue\": 45.4,\n" +
                "\t\t\t\t\"heroStatValue\": 52.3\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"enemyStatValue\": 45.4,\n" +
                "\t\t\t\t\"heroStatValue\": 52.3\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"damageTaken\": [{\n" +
                "\t\t\t\t\"enemyStatValue\": 45.4,\n" +
                "\t\t\t\t\"heroStatValue\": 52.3\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"enemyStatValue\": 45.4,\n" +
                "\t\t\t\t\"heroStatValue\": 52.3\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"enemyStatValue\": 45.4,\n" +
                "\t\t\t\t\"heroStatValue\": 52.3\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"gold\": [{\n" +
                "\t\t\t\t\"enemyStatValue\": 45.4,\n" +
                "\t\t\t\t\"heroStatValue\": 52.3\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"enemyStatValue\": 45.4,\n" +
                "\t\t\t\t\"heroStatValue\": 52.3\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"enemyStatValue\": 45.4,\n" +
                "\t\t\t\t\"heroStatValue\": 52.3\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"xp\": [{\n" +
                "\t\t\t\t\"enemyStatValue\": 45.4,\n" +
                "\t\t\t\t\"heroStatValue\": 52.3\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"enemyStatValue\": 45.4,\n" +
                "\t\t\t\t\"heroStatValue\": 52.3\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"enemyStatValue\": 45.4,\n" +
                "\t\t\t\t\"heroStatValue\": 52.3\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t}\n" +
                "}"
    }
}