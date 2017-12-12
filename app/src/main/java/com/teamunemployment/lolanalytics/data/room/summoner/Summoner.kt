package com.teamunemployment.lolanalytics.data.room.summoner

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Josiah Kendall
 */
@Entity
data class Summoner(@PrimaryKey(autoGenerate = false) val summonerId : Long,
                    val summonerName : String,
                    val summonerDivisionLevel : Int,
                    val summonerDivision : String,
                    val region : String)