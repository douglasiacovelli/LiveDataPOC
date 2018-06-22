package com.iacovelli.livedatapoc.login

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.iacovelli.livedatapoc.R
import com.nhaarman.mockito_kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginFormValidatorTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val emailObserver: Observer<Int> = mock()
    private val passwordObserver: Observer<Int> = mock()

    private val validator = LoginFormValidator()
    private val VALID_PASSWORD = "12345678"
    private val VALID_EMAIL = "douglas@mail.com"

    @Before
    fun setup() {
        validator.emailError.observeForever(emailObserver)
        validator.passwordError.observeForever(passwordObserver)
    }

    @Test
    fun whenEmailIsEmptyItShouldReturnFalse() {
        val isValid = validator.isValid("", VALID_PASSWORD)
        verify(emailObserver).onChanged(R.string.error_required)
        verify(passwordObserver, never()).onChanged(anyInt())
        assertFalse(isValid)
    }

    @Test
    fun whenEmailIsNotValidItShouldReturnFalse() {
        val isValid = validator.isValid("abcd.com", VALID_PASSWORD)
        verify(emailObserver).onChanged(R.string.invalid_email)
        verify(passwordObserver, never()).onChanged(anyInt())
        assertFalse(isValid)
    }

    @Test
    fun whenPasswordIsEmptyItShouldReturnFalse() {
        val isValid = validator.isValid(VALID_EMAIL, "")
        verify(passwordObserver).onChanged(R.string.error_required)
        verify(emailObserver, never()).onChanged(anyInt())
        assertFalse(isValid)
    }

    @Test
    fun whenPasswordIsNotValidItShouldReturnFalse() {
        val isValid = validator.isValid(VALID_EMAIL, "123")
        verify(passwordObserver).onChanged(R.string.invalid_password)
        verify(emailObserver, never()).onChanged(anyInt())
        assertFalse(isValid)
    }

    @Test
    fun whenPasswordAndEmailAreValidItShouldReturnTrue() {
        val isValid = validator.isValid(VALID_EMAIL, VALID_PASSWORD)
        verify(passwordObserver, never()).onChanged(anyInt())
        verify(emailObserver, never()).onChanged(anyInt())
        assertTrue(isValid)
    }
}