package com.teamunemployment.lolanalytics.login.di

import android.arch.persistence.room.Room
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.room.Database
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import com.teamunemployment.lolanalytics.login.onboarding.OnboardingInteractor
import com.teamunemployment.lolanalytics.login.onboarding.OnboardingPresenter
import org.koin.android.module.AndroidModule

/**
 * Created by Josiah Kendall
 */
class OnboardingModule : AndroidModule() {

    override fun context() = applicationContext {

        context(name = "OnboardingModule") {
            provide { OnboardingPresenter(get()) }
            provide { OnboardingInteractor(get(),get())}
            provide { Room.inMemoryDatabaseBuilder(get(), Database::class.java).build() }
            provide { RetrofitFactory() }
        }
    }
}