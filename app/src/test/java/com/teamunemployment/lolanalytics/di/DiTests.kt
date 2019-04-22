package com.teamunemployment.lolanalytics.di

import android.app.Application
import android.content.Context
import com.teamunemployment.lolanalytics.login_page.di.SignInModule
import com.teamunemployment.lolanalytics.io.di.IoModule
import org.junit.Test
import org.koin.Koin
import org.koin.android.ext.koin.init
import org.koin.android.module.AndroidModule
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Created by Josiah Kendall
 */
class DiTests {

    @Test
    fun `Dry Run`() {
        val mockApp = mock(Application::class.java)
        val mockContext = mock(Context::class.java)
        val mockBaseContext = mock(Application::class.java)
        `when`(mockApp.applicationContext).thenReturn(mockBaseContext)
        `when`(mockBaseContext.baseContext).thenReturn(mockContext)

        val koinContext = Koin().init(mockApp).build(allModules()).dryRun()
    }

    private fun allModules() : List<AndroidModule> {
        val modulesList = ArrayList<AndroidModule>()
        modulesList.add(SignInModule())
        modulesList.add(IoModule())
        return modulesList
    }
}