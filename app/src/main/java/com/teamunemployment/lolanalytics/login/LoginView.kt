package com.teamunemployment.lolanalytics.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Toast

import com.teamunemployment.lolanalytics.FrontPage.BaseActivityView
import com.teamunemployment.lolanalytics.R

import kotlinx.android.synthetic.main.login_view.*
import org.koin.android.ext.android.inject

/**
 * @author Josiah Kendall
 *
 * Simple login screen for new users.
 */
class LoginView : AppCompatActivity(), LoginContract.LoginView {
    override fun getUserName(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRegion(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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

        loginWithCredentials.setOnClickListener({
            // Actually replace this
            presenter.handleLogin()
            showMessage("Error: Analysis.gg is not available in your region yet.")
        })
    }

    override fun launchHomeActivity() {
        val mainIntent = Intent(this, BaseActivityView::class.java)
        startActivity(mainIntent)
    }

    override fun setRegionSpinnerAdapter(adapter: ArrayAdapter<CharSequence>) {
        regionSpinner!!.adapter = adapter
    }
}
