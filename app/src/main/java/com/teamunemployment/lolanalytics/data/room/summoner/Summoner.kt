package com.teamunemployment.lolanalytics.data.room.summoner

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Josiah Kendall
 */
@Entity
class Summoner(@PrimaryKey(autoGenerate = false) val summonerId : String,
                    val summonerName : String,
                    val summonerDivisionLevel : Int,
                    val summonerDivision : String,
                    val region : String)