package com.teamunemployment.lolanalytics.login_page.onboarding

import com.teamunemployment.lolanalytics.login_page.onboarding.model.SyncProgress

/**
 * Created by Josiah Kendall
 */
class OnboardingPresenter(private val onboardingInteractor: OnboardingInteractor) {

    private lateinit var view : OnboardingContract.View
    private val messages = OnboardingMessages()

    fun setView(view : OnboardingContract.View) {
        this.view = view
    }

    /**
     * Do any setup shit required for this screen. Currently that is just that it kicks off a sync
     */
    fun start(summonerId : String) {
        if (summonerId == (-1).toString()) {
            // TODO handle this
        }

        onboardingInteractor.requestSync(this, summonerId)
//        TODO("Fetch user details about name, rank etc and show it at the top of the screen")
    }

    /**
     * @param syncProgress The object holding the info we need.
     */
    fun handleSyncProgressUpdate(syncProgress: SyncProgress) : Boolean {
        view.setTotalMatches(syncProgress.total)
        view.setSyncedMatches(syncProgress.completed)

        val finished = syncProgress.total == syncProgress.completed
        if (finished) {
//            view.showProcessingStarted() todo
        }

        return finished
    }

    /**
     * Handle the sync result. Launches the main screen if true, else it will show an error to the user.
     * @param success   The result of the sync. True means the user synced successfully, so progress
     *                  to the home screen.
     */
    fun handleSyncResult(success : Boolean) {
        // launch main screen
        if (!success) {
            view.showMessage(messages.syncFailure())
            return
        }

    }

    fun launchHome() {
        view.launchHomeScreen()
    }

    fun showMessage(message: String) {
        view.showMessage(message)
    }
}