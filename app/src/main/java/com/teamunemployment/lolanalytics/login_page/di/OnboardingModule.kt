package com.teamunemployment.lolanalytics.login_page.di

import android.arch.persistence.room.Room
import com.teamunemployment.lolanalytics.data.room.AppDatabase
import com.teamunemployment.lolanalytics.login_page.onboarding.OnboardingInteractor
import com.teamunemployment.lolanalytics.login_page.onboarding.OnboardingPresenter
import org.koin.android.module.AndroidModule

/**
 * Created by Josiah Kendall
 */
class OnboardingModule : AndroidModule() {

    override fun context() = applicationContext {

        context(name = "OnboardingModule") {
            provide { OnboardingPresenter(get()) }
            provide { OnboardingInteractor(get(),get())}
            provide<AppDatabase>  { Room.databaseBuilder(get(), AppDatabase::class.java, "database1").fallbackToDestructiveMigration().build() }
        }
    }
}