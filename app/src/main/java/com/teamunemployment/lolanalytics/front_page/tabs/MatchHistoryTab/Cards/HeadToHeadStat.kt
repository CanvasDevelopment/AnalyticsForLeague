package com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Cards

import android.arch.persistence.room.Entity
import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * @author Josiah Kendall
 */
@Entity
class HeadToHeadStat(val enemyStatValue : Float,
                     val heroStatValue : Float) {
    override fun equals(other: Any?): Boolean {

        if (other is HeadToHeadStat) {
            return enemyStatValue == other.enemyStatValue &&
                    heroStatValue == other.heroStatValue
        }

        return false
    }
}
