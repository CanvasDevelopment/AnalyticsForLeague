package com.teamunemployment.lolanalytics.login.onboarding

import android.app.Activity
import android.os.Bundle
import com.teamunemployment.lolanalytics.R
import org.koin.android.ext.android.inject

/**
 * Created by Josiah Kendall
 */
class OnboardingView : Activity(), OnboardingContract.View {

    val presenter by inject<OnboardingPresenter>()

    override fun launchHomeScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.onboarding_view)
        presenter.setView(this)
        val summonerId = intent.getLongExtra("summoner_id", -1)
        if (summonerId != (-1).toLong()) {
            presenter.start(summonerId)
        }
    }

    override fun showMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}