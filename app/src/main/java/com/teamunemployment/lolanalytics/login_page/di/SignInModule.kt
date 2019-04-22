package com.teamunemployment.lolanalytics.login_page.di

import android.content.Context
import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.login_page.sign_in.ArrayAdapterFactory
import com.teamunemployment.lolanalytics.login_page.sign_in.LoginInteractor
import com.teamunemployment.lolanalytics.login_page.sign_in.LoginPresenter
import org.koin.android.module.AndroidModule

/**
 * Created by Josiah Kendall
 */
class SignInModule : AndroidModule() {
    override fun context() = applicationContext {

        context(name = "LoginActivity") {
            provide { Network(get()) }
//            provide { RetrofitFactory() }

            provide { LoginInteractor(get(), get(), get(), get()) }
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