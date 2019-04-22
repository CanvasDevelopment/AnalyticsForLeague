package com.teamunemployment.lolanalytics.mock

/**
 * @author Josiah Kendall
 *
 * Just a class to store mock responses for testing the app without needing a server.
 */
class MockMatchHistoryServiceResponses {

    fun getMatchList() : String {
        return "{\n" +
                "resultCode: 200,\n" +
                "data: [\n" +
                "{\n" +
                "matchId: \"215406321\",\n" +
                "role: \"DUO_SUPPORT\",\n" +
                "lane: \"BOTTOM\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"215370040\",\n" +
                "role: \"DUO_SUPPORT\",\n" +
                "lane: \"TOP\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"215357772\",\n" +
                "role: \"DUO_CARRY\",\n" +
                "lane: \"BOTTOM\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"215355027\",\n" +
                "role: \"SOLO\",\n" +
                "lane: \"BOTTOM\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"215349585\",\n" +
                "role: \"DUO_SUPPORT\",\n" +
                "lane: \"NONE\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"215347136\",\n" +
                "role: \"DUO\",\n" +
                "lane: \"TOP\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"215333121\",\n" +
                "role: \"DUO\",\n" +
                "lane: \"TOP\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"215332125\",\n" +
                "role: \"DUO\",\n" +
                "lane: \"BOTTOM\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"215331577\",\n" +
                "role: \"DUO_SUPPORT\",\n" +
                "lane: \"NONE\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"215330942\",\n" +
                "role: \"DUO_SUPPORT\",\n" +
                "lane: \"NONE\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"215294018\",\n" +
                "role: \"DUO_CARRY\",\n" +
                "lane: \"TOP\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"215150449\",\n" +
                "role: \"DUO_CARRY\",\n" +
                "lane: \"BOTTOM\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"215135639\",\n" +
                "role: \"DUO_CARRY\",\n" +
                "lane: \"BOTTOM\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"215129205\",\n" +
                "role: \"NONE\",\n" +
                "lane: \"JUNGLE\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"215124519\",\n" +
                "role: \"NONE\",\n" +
                "lane: \"JUNGLE\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"215123305\",\n" +
                "role: \"DUO_CARRY\",\n" +
                "lane: \"BOTTOM\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"215077834\",\n" +
                "role: \"DUO\",\n" +
                "lane: \"TOP\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"215066750\",\n" +
                "role: \"SOLO\",\n" +
                "lane: \"MID\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"215031390\",\n" +
                "role: \"SOLO\",\n" +
                "lane: \"MID\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"215030123\",\n" +
                "role: \"DUO_SUPPORT\",\n" +
                "lane: \"TOP\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"214732273\",\n" +
                "role: \"DUO_SUPPORT\",\n" +
                "lane: \"NONE\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"214730444\",\n" +
                "role: \"DUO\",\n" +
                "lane: \"MID\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"214719326\",\n" +
                "role: \"NONE\",\n" +
                "lane: \"JUNGLE\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"214641236\",\n" +
                "role: \"SOLO\",\n" +
                "lane: \"MID\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"214640967\",\n" +
                "role: \"DUO_SUPPORT\",\n" +
                "lane: \"NONE\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"214632158\",\n" +
                "role: \"SOLO\",\n" +
                "lane: \"MID\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"214620961\",\n" +
                "role: \"DUO_SUPPORT\",\n" +
                "lane: \"MID\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"214599901\",\n" +
                "role: \"DUO_SUPPORT\",\n" +
                "lane: \"TOP\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"214442929\",\n" +
                "role: \"DUO_SUPPORT\",\n" +
                "lane: \"NONE\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"214333862\",\n" +
                "role: \"DUO_SUPPORT\",\n" +
                "lane: \"NONE\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"214332866\",\n" +
                "role: \"SOLO\",\n" +
                "lane: \"TOP\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"214331891\",\n" +
                "role: \"DUO_SUPPORT\",\n" +
                "lane: \"MID\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"214285567\",\n" +
                "role: \"SOLO\",\n" +
                "lane: \"MID\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"214277477\",\n" +
                "role: \"DUO_SUPPORT\",\n" +
                "lane: \"NONE\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"214275792\",\n" +
                "role: \"DUO\",\n" +
                "lane: \"NONE\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"214274295\",\n" +
                "role: \"DUO\",\n" +
                "lane: \"BOTTOM\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"214272532\",\n" +
                "role: \"DUO_CARRY\",\n" +
                "lane: \"BOTTOM\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"214108061\",\n" +
                "role: \"DUO_SUPPORT\",\n" +
                "lane: \"NONE\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"214106589\",\n" +
                "role: \"SOLO\",\n" +
                "lane: \"TOP\",\n" +
                "},\n" +
                "{\n" +
                "matchId: \"214061065\",\n" +
                "role: \"SOLO\",\n" +
                "lane: \"MID\",\n" +
                "},\n" +
                "],\n" +
                "}"
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
                "\t\t\"creeps\": [{\n" +
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