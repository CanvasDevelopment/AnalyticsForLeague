package com.teamunemployment.lolanalytics.data.source

import org.koin.android.module.AndroidModule

class DatasourceModule : AndroidModule() {
    override fun context()= applicationContext {
        context(name = "BaseModule") {
            provide {MatchHistoryDataSource(get())}
        }
    }
}