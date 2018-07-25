package com.iacovelli.livedatapoc.login

import android.arch.lifecycle.MutableLiveData
import com.iacovelli.livedatapoc.R

class LoginFormValidator {
    val emailError = MutableLiveData<Int>()
    val passwordError = MutableLiveData<Int>()

    fun isValid(email: String?, password: String?): Boolean {
        val emailValid = isEmailValid(email)
        val passwordValid = isPasswordValid(password)

        return emailValid && passwordValid
    }

    fun isPasswordValid(passwordString: String?): Boolean {
        if (passwordString.isNullOrBlank()) {
            passwordError.value = R.string.error_required
            return false
        }
        if (passwordString!!.length < 8) {
            passwordError.value = R.string.invalid_password
            return false
        }
        passwordError.value = null
        return true
    }

    fun isEmailValid(emailString: String?): Boolean {
        if (emailString.isNullOrBlank()) {
            emailError.value = R.string.error_required
            return false
        }
        if (!emailString!!.contains("@")){
            emailError.value = R.string.invalid_email
            return false
        }
        emailError.value = null
        return true
    }
}