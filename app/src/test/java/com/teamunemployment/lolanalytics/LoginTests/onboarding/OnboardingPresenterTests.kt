package com.teamunemployment.lolanalytics.LoginTests.onboarding

import com.teamunemployment.lolanalytics.login.onboarding.OnboardingContract
import com.teamunemployment.lolanalytics.login.onboarding.OnboardingInteractor
import com.teamunemployment.lolanalytics.login.onboarding.OnboardingMessages
import com.teamunemployment.lolanalytics.login.onboarding.OnboardingPresenter
import org.junit.Test
import org.mockito.Mockito.*

/**
 * Created by Josiah Kendall
 */
class OnboardingPresenterTests {

    private val presenter = OnboardingPresenter(OnboardingInteractor())
    private val view = mock(OnboardingContract.View::class.java)
    private val messages = OnboardingMessages()

    @Test
    fun `Make sure that we show the sync error message to the user if we fail the sync`() {
        presenter.setView(view)
        presenter.handleSyncResult(false)
        verify(view, times(1)).showMessage(messages.syncFailure())
    }

    @Test
    fun `Make sure the we launch the home screen on successfull sync`() {

    }
}