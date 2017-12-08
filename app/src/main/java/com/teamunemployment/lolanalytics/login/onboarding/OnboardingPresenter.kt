package com.teamunemployment.lolanalytics.login.onboarding

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
    fun start() {
        onboardingInteractor.requestSync(this)
        // fetch user details to display - cos it will look pretty sweet
    }

    /**
     * @param matchesProcessed How many matches have been processed by the server
     * @param totalMatches The total number of matches that need to be processed.
     */
    fun handleSyncProgressUpdate(matchesProcessed : Int, totalMatches : Int) {
        // update the view - waiting on server functionality
        TODO()
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
        }
    }
}