package com.teamunemployment.lolanalytics.login.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.teamunemployment.lolanalytics.io.RESTApiExecutor
import com.teamunemployment.lolanalytics.login.sign_in.ArrayAdapterFactory
import com.teamunemployment.lolanalytics.login.sign_in.LoginModel
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
            provide { LoginModel(get(), get()) }
            provide { LoginPresenter(get(), get()) }
            provide { ArrayAdapterFactory(get()) }
            provide { applicationContext.baseContext!!} bind Context::class
            provide { val retrofit : Retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("https://lolanalyticsv3.appspot.com/_ah/api/myApi/v1/")
                    .build()

                retrofit.create(LoginRemoteRepo::class.java)}
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