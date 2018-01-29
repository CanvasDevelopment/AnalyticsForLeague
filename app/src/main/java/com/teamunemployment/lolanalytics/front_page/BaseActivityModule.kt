package com.teamunemployment.lolanalytics.front_page

import android.arch.persistence.room.Room
import com.teamunemployment.lolanalytics.data.room.Database
import com.teamunemployment.lolanalytics.login.onboarding.OnboardingInteractor
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