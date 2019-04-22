package com.teamunemployment.lolanalytics.data

import android.arch.persistence.room.TypeConverter

import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.HeadToHeadStat

class HeadToHeadStatConverter {

    @TypeConverter
    fun headToHeadStatFromString(value: String): HeadToHeadStat {
        val headToHeadVals = value.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return HeadToHeadStat(java.lang.Float.parseFloat(headToHeadVals[0]), java.lang.Float.parseFloat(headToHeadVals[1]))
    }

    @TypeConverter
    fun languagesToStoredString(headToHeadStat: HeadToHeadStat): String {
        return headToHeadStat.enemyStatValue.toString() + "/" + headToHeadStat.heroStatValue
    }
}
