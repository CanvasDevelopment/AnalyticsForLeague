package com.teamunemployment.lolanalytics.login.sign_in

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.teamunemployment.lolanalytics.R
import com.teamunemployment.lolanalytics.Utils.RegionHelper

import javax.inject.Inject

/**
 * @author Josiah Kendall
 */
class LoginPresenter @Inject
constructor(private val arrayAdapterFactory: ArrayAdapterFactory,
            private val loginInteractor: LoginInteractor) : LoginContract.Presenter, TextWatcher {

    private lateinit var view: LoginContract.LoginView
    private val loginErrors = LoginErrorMessages()

    override fun start() {
        setRegionAdapter()
    }

    override fun handleError(e: Throwable) {
        view.showMessage(e.message)
        view.showLoginButton()
        view.hideProgressSpinner()
    }

    override fun restart() {
        view.showLoginButton()
        view.hideProgressSpinner()
    }

    override fun resume() {

    }

    override fun pause() {

    }

    override fun stop() {

    }

    override fun destroy() {

    }

    override fun loginTestUser() {
//        view.launchOnboardingActivity()
    }

    override fun setView(loginView: LoginContract.LoginView) {
        this.view = loginView
    }

    override fun requestUserRegistration() {
        val userName = view.userName
        val region = view.region
        view.hideLoginButton()
        view.showLoginProgressSpinner()
        loginInteractor.registerAUser(userName, region, this)
    }

    /**
     * Set the correct registerAUser result to the view.
     * @param loginResult The result returned from the registerAUser attempt.
     *                      Codes :
     *                          404: The user does not exist
     *                          500: The server failed to log you in.
     *                          200: Ok
     *                          -1 : General error message.
     */
    override fun handleRegisterUserResult(code: Int, summonerId : Long, region : String) {
        when(code) {
            404 -> view.showMessage(loginErrors.`404`())
            500 -> view.showMessage(loginErrors.`500`())
            -1 -> view.showMessage(loginErrors.default())
            else -> {
                view.launchOnboardingActivity(summonerId)
            }
        }
    }

    private fun setRegionAdapter() {
        val adapter = arrayAdapterFactory.getArrayAdapter(R.array.regions_array,
                android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(R.layout.region_spinner_item)
        view.setRegionSpinnerAdapter(adapter)
    }

    override fun afterTextChanged(s: Editable?) {
        if (s == null) {
            return
        }
        handleTextChanged(s.toString())
    }

    fun handleTextChanged(s: String) {
        when(s.isEmpty()) {
            true -> {view.disableProceed()}
            false -> {view.enableProceed()}
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // not required
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (s == null) {
            view.disableProceed()
            return
        }
        handleTextChanged(s.toString())
    }
}
