package com.teamunemployment.lolanalytics.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Champ(val version :String,
            val id : String,
            val key : String,
            val name : String,
            val title : String,
            val blurb : String,
            val games : Int,
            val summonerId : String) {
    @PrimaryKey(autoGenerate = true)
    var _id : Int = 0
}