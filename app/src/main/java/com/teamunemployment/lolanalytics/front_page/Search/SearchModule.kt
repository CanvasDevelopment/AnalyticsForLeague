package com.teamunemployment.lolanalytics.front_page.Search

import com.teamunemployment.lolanalytics.front_page.BaseActivityPersistenceInteractor
import com.teamunemployment.lolanalytics.front_page.BaseActivityPresenter
import org.koin.android.module.AndroidModule

/**
 * @author Josiah Kendall
 */
class SearchModule : AndroidModule() {
    override fun context() = applicationContext {

        context(name = "SearchModule") {
            provide { SearchInteractor() }
            provide { SearchPresenter(get()) }
        }
    }
}