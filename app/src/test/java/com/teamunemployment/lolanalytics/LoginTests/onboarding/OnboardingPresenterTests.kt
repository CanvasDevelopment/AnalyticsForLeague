package com.teamunemployment.lolanalytics.LoginTests.onboarding

import com.teamunemployment.lolanalytics.Utils.Network
import com.teamunemployment.lolanalytics.io.networking.RetrofitFactory
import com.teamunemployment.lolanalytics.login_page.onboarding.*
import com.teamunemployment.lolanalytics.login_page.onboarding.model.SyncProgress
import org.junit.Test
import org.mockito.Mockito.*

/**
 * Created by Josiah Kendall
 */
class OnboardingPresenterTests {

    private val retrofitFactory = mock(RetrofitFactory::class.java)
    private val network = mock(Network::class.java)
    private val presenter = OnboardingPresenter(OnboardingInteractor(retrofitFactory,network))
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
        presenter.setView(view)
        presenter.handleSyncResult(true)
        verify(view, times(1)).launchHomeScreen()
    }

    @Test
    fun `Make sure that we set the total matches when setting sync progress update`() {
        presenter.setView(view)
        val syncProgress = SyncProgress(121, 9)
        presenter.handleSyncProgressUpdate(syncProgress)
        verify(view, times(1)).setTotalMatches(syncProgress.total)
    }

    @Test
    fun `Make sure that we set the completed matches when setting sync progress update`() {
        presenter.setView(view)
        val syncProgress = SyncProgress(121, 9)
        presenter.handleSyncProgressUpdate(syncProgress)
        verify(view, times(1)).setSyncedMatches(syncProgress.completed)
    }

    @Test
    fun `Make sure that we return true when the completed matches are the same as the total matches`() {
        presenter.setView(view)
        val syncProgress = SyncProgress(121, 121)
        val result = presenter.handleSyncProgressUpdate(syncProgress)
        assert(result)
    }

    @Test
    fun `Make sure that we return false when the completed matches are not the same as the total matches`() {
        presenter.setView(view)
        val syncProgress = SyncProgress(121, 9)
        val result = presenter.handleSyncProgressUpdate(syncProgress)
        assert(!result)

    }
}