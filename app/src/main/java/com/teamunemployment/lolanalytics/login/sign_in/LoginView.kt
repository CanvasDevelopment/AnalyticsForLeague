package com.teamunemployment.lolanalytics.login.sign_in

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Toast

import com.teamunemployment.lolanalytics.front_page.BaseActivityView
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.login.onboarding.OnboardingView

import kotlinx.android.synthetic.main.login_view.*
import org.koin.android.ext.android.inject

/**
 * @author Josiah Kendall
 *
 * Simple registerAUser screen for new users.
 */
class LoginView : AppCompatActivity(), LoginContract.LoginView {

    val presenter by inject<LoginPresenter>()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_view)
        bindButtons()
        presenter.setView(this)
        presenter.start()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun bindButtons() {
        loginWithCredentials.setOnClickListener { presenter.requestSync() }
    }

    override fun launchOnboardingActivity(summonerId : Long) {
        val mainIntent = Intent(this, OnboardingView::class.java)
        mainIntent.putExtra("summoner_id", summonerId)
        startActivity(mainIntent)
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up )
    }

    override fun setRegionSpinnerAdapter(adapter: ArrayAdapter<CharSequence>) {
        regionSpinner!!.adapter = adapter
    }

    override fun getUserName(): String {
        return user_name.text.toString()
    }

    override fun getRegion(): String {
        return regionSpinner.selectedItem.toString()
    }
}
