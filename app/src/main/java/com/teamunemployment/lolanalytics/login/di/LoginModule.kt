package com.teamunemployment.lolanalytics.login.di

import android.content.Context
import com.teamunemployment.lolanalytics.login.ArrayAdapterFactory
import com.teamunemployment.lolanalytics.login.LoginModel
import com.teamunemployment.lolanalytics.login.LoginPresenter
import org.koin.android.module.AndroidModule

/**
 * Created by Josiah Kendall
 */
class LoginModule : AndroidModule() {
    override fun context() = applicationContext {

        context(name = "LoginActivity") {
            provide { LoginModel(get(), get()) }
            provide { LoginPresenter(get(), get()) }
            provide { ArrayAdapterFactory(get()) }
            provide {applicationContext.baseContext!!} bind Context::class
//            provide { context }

        }
    }

}

//    @Provides
//    ArrayAdapterFactory provideArrayAdapterFactory(Context context) {
//        return new ArrayAdapterFactory(context);
//    }
//
//    @Provides
//    LoginModel provideLoginModel(RESTApiExecutor restApiExecutor, RealmExecutor realmExecutor) {
//        return  new LoginModel(restApiExecutor, realmExecutor);
//    }
//
//    @Provides
//    LoginPresenter provideLoginPresenter(ArrayAdapterFactory arrayAdapterFactory, LoginModel loginModel) {
//        return new LoginPresenter(arrayAdapterFactory, loginModel);
//    }