package com.teamunemployment.lolanalytics.login.di

import android.arch.persistence.room.Room
import android.content.Context
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.room.Database
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import com.teamunemployment.lolanalytics.login.sign_in.ArrayAdapterFactory
import com.teamunemployment.lolanalytics.login.sign_in.LoginInteractor
import com.teamunemployment.lolanalytics.login.sign_in.LoginPresenter
import com.teamunemployment.lolanalytics.login.sign_in.LoginRemoteRepo
import org.koin.android.module.AndroidModule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Josiah Kendall
 */
class SignInModule : AndroidModule() {
    override fun context() = applicationContext {

        context(name = "LoginActivity") {
            provide { Network(get()) }
//            provide { RetrofitFactory() }

            provide { LoginInteractor(get(), get(), get()) }
            provide { LoginPresenter(get(), get()) }
            provide { ArrayAdapterFactory(get()) }
            provide { applicationContext.baseContext!!} bind Context::class
        }
    }

}

//    @Provides
//    ArrayAdapterFactory provideArrayAdapterFactory(Context context) {
//        return new ArrayAdapterFactory(context);
//    }
//
//    @Provides
//    LoginInteractor provideLoginModel(RESTApiExecutor restApiExecutor, RealmExecutor realmExecutor) {
//        return  new LoginInteractor(restApiExecutor, realmExecutor);
//    }
//
//    @Provides
//    LoginPresenter provideLoginPresenter(ArrayAdapterFactory arrayAdapterFactory, LoginInteractor loginInteractor) {
//        return new LoginPresenter(arrayAdapterFactory, loginInteractor);
//    }