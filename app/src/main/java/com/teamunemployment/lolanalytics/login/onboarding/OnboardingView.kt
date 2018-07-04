package com.teamunemployment.lolanalytics.login.onboarding

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.front_page.BaseActivityView
import kotlinx.android.synthetic.main.onboarding_view.*
import org.koin.android.ext.android.inject

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
        val summonerId = intent.getLongExtra("summoner_id", -1)

        if (summonerId != (-1).toLong()) {
            presenter.start(summonerId)
        }
    }

    override fun showMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTotalMatches(matches: Int) {
        sync_progress.max = matches
    }

    override fun setSyncedMatches(matches: Int) {
        sync_progress.progress = matches
    }

}