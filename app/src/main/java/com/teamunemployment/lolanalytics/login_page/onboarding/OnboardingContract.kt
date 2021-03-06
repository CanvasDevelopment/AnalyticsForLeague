package com.teamunemployment.lolanalytics.login_page.onboarding

/**
 * Created by Josiah Kendall
 */
interface OnboardingContract {
    interface View {
        fun showMessage(message : String)
        fun launchHomeScreen()
        fun setTotalMatches(matches : Int)
        fun setSyncedMatches(matches: Int)
    }

    interface Presenter {

    }
}