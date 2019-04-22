package com.teamunemployment.lolanalytics.login_page.onboarding

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.front_page.BaseActivityView
import kotlinx.android.synthetic.main.onboarding_view.*
import org.koin.android.ext.android.inject
import timber.log.Timber

/**
 * Created by Josiah Kendall
 */
class OnboardingView : Activity(), OnboardingContract.View {

    val presenter by inject<OnboardingPresenter>()

    override fun launchHomeScreen() {
        val mainIntent = Intent(this, BaseActivityView::class.java)
//        mainIntent.putExtra("summoner_id", summonerId)
        startActivity(mainIntent)
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.onboarding_view)
        presenter.setView(this)
        val summonerId : String? = intent.getStringExtra("summoner_id")

        if (summonerId != null) {
            presenter.start(summonerId!!)
        }
    }

    override fun showMessage(message: String) {
        Timber.d(message)
    }

    override fun setTotalMatches(matches: Int) {
        sync_progress.max = matches
    }

    override fun setSyncedMatches(matches: Int) {
        sync_progress.progress = matches
    }

}