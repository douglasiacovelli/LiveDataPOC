package com.iacovelli.livedatapoc.login

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.iacovelli.livedatapoc.FakeSchedulers
import com.iacovelli.livedatapoc.R
import com.iacovelli.livedatapoc.common.SingleEvent
import com.iacovelli.livedatapoc.model.LoginCredential
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Completable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val messageObserver: Observer<SingleEvent<Int>> = mock()
    private val userLoggedObserver: Observer<SingleEvent<Boolean>> = mock()
    private val formValidator: LoginFormValidator = mock()
    private val repository: LoginRepository = mock()
    private val messageCaptor = argumentCaptor<SingleEvent<Int>>()

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        viewModel = LoginViewModel(repository, formValidator, FakeSchedulers())
        viewModel.message.observeForever(messageObserver)
        viewModel.userLogged.observeForever(userLoggedObserver)
    }

    @Test
    fun whenFormIsInvalidItShouldNotCallRepository() {
        viewModel.onUserSubmit()

        verify(messageObserver).onChanged(messageCaptor.capture())
        verify(userLoggedObserver, never()).onChanged(anyOrNull())
        assertEquals(R.string.invalid_form, messageCaptor.firstValue.getContent())
        verify(repository, never()).login(anyOrNull())
    }

    @Test
    fun whenFormIsValidItShouldCallRepository() {
        val loginCredentialCaptor = argumentCaptor<LoginCredential>()
        val userLoggedCaptor = argumentCaptor<SingleEvent<Boolean>>()

        val email = "user@mail.com"
        val password = "password"
        viewModel.email.value = email
        viewModel.password.value = password

        given(formValidator.isValid(anyString(), anyString()))
                .willReturn(true)
        given(repository.login(any())).willReturn(Completable.complete())

        viewModel.onUserSubmit()
        verify(repository).login(loginCredentialCaptor.capture())

        assertEquals(email, loginCredentialCaptor.firstValue.email)
        assertEquals(password, loginCredentialCaptor.firstValue.password)

        verify(userLoggedObserver).onChanged(userLoggedCaptor.capture())
        assertTrue(userLoggedCaptor.firstValue.getContent()!!)

        verify(messageObserver, never()).onChanged(anyOrNull())
    }
}