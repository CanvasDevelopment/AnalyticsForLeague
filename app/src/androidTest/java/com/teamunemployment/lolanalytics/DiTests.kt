package com.teamunemployment.lolanalytics

import android.app.Application
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.teamunemployment.lolanalytics.login.di.SignInModule
import com.teamunemployment.lolanalytics.io.di.IoModule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.Koin
import org.koin.android.ext.koin.init
import org.koin.android.module.AndroidModule

/**
 * Created by Josiah Kendall
 */
class DiTests {
// todo fix mockito issues
//    @Test
//    fun `Dry run`() {
//        val context = InstrumentationRegistry.getContext()
//        Koin().init(Mockito.mock(Application::class.java)).build(allModules()).dryRun()
//    }
//
//    private fun allModules() : List<AndroidModule> {
//        val modulesList = ArrayList<AndroidModule>()
//        modulesList.add(SignInModule())
//        modulesList.add(IoModule())
//        return modulesList
//    }
}