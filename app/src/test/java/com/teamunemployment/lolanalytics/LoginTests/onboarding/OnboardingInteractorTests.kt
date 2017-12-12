package com.teamunemployment.lolanalytics.LoginTests.onboarding

import com.teamunemployment.lolanalytics.data.model.Result
import com.teamunemployment.lolanalytics.login.onboarding.OnboardingInteractor
import com.teamunemployment.lolanalytics.login.onboarding.OnboardingPresenter
import com.teamunemployment.lolanalytics.login.onboarding.OnboardingService
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Call

/**
 * Created by Josiah Kendall
 */
class OnboardingInteractorTests {

    private val service = mock(OnboardingService::class.java)
    val interactor = OnboardingInteractor(service)
    val presenter = mock(OnboardingPresenter::class.java)

    @Test
    fun `Make sure that we set true when returning 200`() {
        val call =mock(Call::class.java)
//        val
//        `when`(call.execute()).thenReturn()
        `when`(service.syncUser(-1)).thenReturn(call as Call<Result<String>>?)
//        interactor.requestSync(presenter)
        verify(presenter, times(1)).handleSyncResult(false)
    }

    @Test
    fun `Make sure that we set false when we dont return 200`() {

    }
}