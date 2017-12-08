package com.teamunemployment.lolanalytics.login.onboarding

import android.app.Activity
import android.os.Bundle
import com.teamunemployment.lolanalytics.R

/**
 * Created by Josiah Kendall
 */
class OnboardingView : Activity(), OnboardingContract.View {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.onboarding_view)
    }

    override fun showMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}