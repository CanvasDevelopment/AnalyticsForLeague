package com.teamunemployment.lolanalytics.front_page

import org.koin.android.module.AndroidModule

/**
 * @author Josiah Kendall
 */
class BaseActivityModule : AndroidModule() {
    override fun context() = applicationContext {

        context(name = "BaseActivityModule") {
            provide {BaseActivityPresenter(get())}
            provide { BaseActivityPersistenceInteractor(get(), get(), get()) }
        }
    }
}