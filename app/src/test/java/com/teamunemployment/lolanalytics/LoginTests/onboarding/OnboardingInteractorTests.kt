package com.teamunemployment.lolanalytics.LoginTests.onboarding

import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import com.teamunemployment.lolanalytics.login_page.onboarding.OnboardingInteractor
import com.teamunemployment.lolanalytics.login_page.onboarding.OnboardingPresenter
import com.teamunemployment.lolanalytics.login_page.onboarding.OnboardingService
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Call

/**
 * Created by Josiah Kendall
 */
class OnboardingInteractorTests {

    private val service = mock(OnboardingService::class.java)
    private val retrofitFactory = mock(RetrofitFactory::class.java)
    private val network = mock(Network::class.java)
    val interactor = OnboardingInteractor(retrofitFactory, network)
    val presenter = mock(OnboardingPresenter::class.java)

    @Test
    fun `Make sure that we set true when returning 200`() {
        val call =mock(Call::class.java)

        `when`(service.syncUserMatchList("-1")).thenReturn(call as Call<Result<String>>?)
//        interactor.requestUserRegistration(presenter)
        verify(presenter, times(1)).handleSyncResult(false)
    }

    @Test
    fun `Make sure that we set false when we dont return 200`() {

    }
}