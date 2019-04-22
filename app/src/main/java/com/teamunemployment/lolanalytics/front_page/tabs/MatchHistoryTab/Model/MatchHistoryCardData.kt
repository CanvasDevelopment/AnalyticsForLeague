package com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverter
import android.arch.persistence.room.TypeConverters
import com.teamunemployment.lolanalytics.data.HeadToHeadStatConverter
import com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards.HeadToHeadStat
/**
 * @author Josiah Kendall§
 */
@Entity
class MatchHistoryCardData(@PrimaryKey(autoGenerate = false) val matchId: Long,
                           val champId: Long,
                           val enemyChampId: Long,
                           @TypeConverters(HeadToHeadStatConverter::class) val earlyGame: HeadToHeadStat,
                           @TypeConverters(HeadToHeadStatConverter::class) val midGame: HeadToHeadStat,
                           @TypeConverters(HeadToHeadStatConverter::class) val lateGame: HeadToHeadStat,
                           val won : Boolean,
                           val detailsUrl : String,
                           val summonerId : String) {

    override fun equals(other: Any?): Boolean {
        if (other is MatchHistoryCardData) {
            if (other.matchId == matchId &&
                    other.champId == champId &&
                    other.enemyChampId == enemyChampId &&
                    other.earlyGame == earlyGame &&
                    other.midGame == midGame &&
                    other.lateGame == lateGame &&
                    other.won == won &&
                    other.detailsUrl == detailsUrl &&
                    other.summonerId == summonerId) {
                return true
            }
        }
        return false
    }
}
