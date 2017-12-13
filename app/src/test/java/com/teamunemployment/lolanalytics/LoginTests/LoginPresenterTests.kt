package com.teamunemployment.lolanalytics.LoginTests

import android.widget.ArrayAdapter
import com.teamunemployment.lolanalytics.login.sign_in.*
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.*

/**
 * Created by Josiah Kendall
 */
class LoginPresenterTests {

    @Captor
    private lateinit var arrayAdapterCaptor : ArgumentCaptor<ArrayAdapter<CharSequence>>

    private lateinit var arrayAdapterFactory : ArrayAdapterFactory
    private lateinit var loginInteractor: LoginInteractor
    private lateinit var presenter : LoginPresenter
    private val view = mock(LoginContract.LoginView::class.java)
    private val loginErrorMessages = LoginErrorMessages()

    @Before
    fun `Do The Mahi`() {
        MockitoAnnotations.initMocks(this)
        arrayAdapterFactory = mock(ArrayAdapterFactory::class.java)
        loginInteractor = mock(LoginInteractor::class.java)
        presenter = LoginPresenter(arrayAdapterFactory, loginInteractor)
    }

    @Test
    fun `Make sure that we can handle a 404 when logging in a user`() {

        presenter.setView(view)
        presenter.handleSyncResult(404, 123456)
        verify(view, times(1)).showMessage(loginErrorMessages.`404`())
    }

    @Test
    fun `Make sure that we hide the login button while login is progressing`() {
        presenter.setView(view)
        `when`(view.userName).thenReturn("work cunt")
        `when`(view.region).thenReturn("kotlin is dope")
        presenter.requestSync()
        verify(view, times(1)).hideLoginButton()
    }

    @Test
    fun `Make sure that we show the login button again if we fail`() {
        presenter.setView(view)
        presenter.handleError(Throwable("IoException or some shit"))
        verify(view, times(1)).showLoginButton()
    }

    @Test
    fun `Make sure that we show the login button again after resuming activity`() {
        presenter.setView(view)
        presenter.restart()
        verify(view, times(1)).showLoginButton()
    }

    @Test
    fun `Make sure that we hide login progress on error`() {
        presenter.setView(view)
        presenter.restart()
        verify(view, times(1)).hideProgressSpinner()
    }

    @Test
    fun `Make sure that the progress spinner is shown while we are fetching the user`() {
        presenter.setView(view)
        `when`(view.userName).thenReturn("work cunt")
        `when`(view.region).thenReturn("kotlin is dope")
        presenter.requestSync()
        verify(view, times(1)).showLoginProgressSpinner()
    }

    @Test
    fun `Make sure that the progress spinner is hidden when we restart`() {
        presenter.setView(view)
        presenter.restart()
        verify(view, times(1)).hideProgressSpinner()
    }




}