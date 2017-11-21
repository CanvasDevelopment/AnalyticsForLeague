package com.teamunemployment.lolanalytics.login

import com.teamunemployment.lolanalytics.R

import javax.inject.Inject

/**
 * @author Josiah Kendall
 */
class LoginPresenter @Inject
constructor(val arrayAdapterFactory: ArrayAdapterFactory, val loginModel: LoginModel) : LoginContract.Presenter {

    private var view: LoginContract.LoginView? = null
    private val loginErrors = LoginErrorMessages()
    override fun start() {
        setRegionAdapter()
    }

    override fun handleError(e: Throwable) {
        view!!.showMessage("Sorry, an error occurred. Please try again")
    }

    override fun restart() {

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
        view!!.launchHomeActivity()
    }

    override fun setView(loginView: LoginContract.LoginView) {
        this.view = loginView
    }

    override fun handleLogin() {
        val userName = view!!.userName
        val region = view!!.region
        loginModel.login(userName, region, this)
    }

    /**
     * Set the correct login result to the view.
     * @param loginResult The result returned from the login attempt.
     *                      Codes :
     *                          404: The user does not exist
     *                          500: The server failed to log you in.
     *                          200: Ok
     */
    override fun handleLoginResult(loginResult: Long) {

        when(loginResult) {
            404.toLong() -> view!!.showMessage(loginErrors.`404`())
            500.toLong() -> view!!.showMessage(loginErrors.`500`())
            200.toLong() -> view!!.launchHomeActivity()
            else -> {
                view!!.showMessage(loginErrors.default())
            }
        }
    }

    private fun setRegionAdapter() {
        val adapter = arrayAdapterFactory.getArrayAdapter(R.array.regions_array,
                android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(R.layout.region_spinner_item)
        view!!.setRegionSpinnerAdapter(adapter)
    }
}
