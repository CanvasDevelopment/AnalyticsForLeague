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

//        `when`(arrayAdapterFactory.getArrayAdapter(Mockito.anyInt(),Mockito.anyInt() )).thenReturn(arrayAdapterCaptor.capture())
        presenter.setView(view)
        presenter.handleSyncResult(404, -1)
        verify(view, times(1)).showMessage(loginErrorMessages.`404`())
    }

}