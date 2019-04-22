package com.teamunemployment.lolanalytics.front_page.champ_menu

import org.koin.android.module.AndroidModule

/**
 * @author Josiah Kendall
 */
class ChampMenuModule : AndroidModule() {
    override fun context() = applicationContext {

        context(name = "SearchModule") {
            provide { ChampMenuInteractor(get(), get(), get(), get(), get()) }
            provide { ChampMenuPresenter(get(), get()) }
        }
    }
}