package com.teamunemployment.lolanalytics.front_page.tabs.MatchHistoryTab.Model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class MatchIdentifier(val matchId : Long,
                      val role : String,
                      val lane : String,
                      val champKey : Int,
                      val timestamp : Long,
                      val summonerId : String) {

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0

    override fun equals(other: Any?): Boolean {
        if (other is MatchIdentifier) {
            return matchId == other.matchId &&
                    role == other.role &&
                    lane == other.lane &&
                    timestamp == other.timestamp &&
                    summonerId == other.summonerId &&
                    id == other.id
        }

        return false
    }

    fun default() : MatchIdentifier {
        return MatchIdentifier(-1,"","",-1,-1, "-1")
    }
}