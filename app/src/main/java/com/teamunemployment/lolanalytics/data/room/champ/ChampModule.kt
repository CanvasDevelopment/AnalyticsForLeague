package com.teamunemployment.lolanalytics.data.room.champ

import com.teamunemployment.lolanalytics.data.room.AppDatabase
import com.teamunemployment.lolanalytics.data.source.MatchHistoryDataSource
import org.koin.android.module.AndroidModule

class ChampModule : AndroidModule() {
    override fun context()= applicationContext {
        context(name = "ChampModule") {
            provide { get<AppDatabase>().champDao() }
            provide { get<AppDatabase>().simpleChampDao() }
        }
    }
}