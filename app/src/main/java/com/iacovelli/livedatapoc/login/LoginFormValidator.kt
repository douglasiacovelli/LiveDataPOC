package com.iacovelli.livedatapoc.login

import android.arch.lifecycle.MutableLiveData
import com.iacovelli.core.bindingadapters.Validator
import com.iacovelli.livedatapoc.R

class LoginFormValidator {
    val emailError = MutableLiveData<Int>()
    val passwordError = MutableLiveData<Int>()
    val phoneError = MutableLiveData<Int>()

    val emailValidator = object: Validator {
        override fun validate(string: String) {
            isEmailValid(string)
        }
    }

    val passwordValidator = object: Validator {
        override fun validate(string: String) {
            isPasswordValid(string)
        }
    }

    val phoneValidator = object: Validator {
        override fun validate(string: String) {
            isPhoneValid(string)
        }
    }

    fun isValid(email: String?, password: String?, phone: String?): Boolean {
        val emailValid = isEmailValid(email)
        val passwordValid = isPasswordValid(password)
        val phoneValid = isPhoneValid(phone)

        return emailValid && passwordValid && phoneValid
    }

    private fun isPasswordValid(passwordString: String?): Boolean {
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

    private fun isEmailValid(emailString: String?): Boolean {
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

    private fun isPhoneValid(phoneString: String?): Boolean {
        if (phoneString.isNullOrBlank()) {
            phoneError.value = R.string.error_required
            return false
        }
        phoneError.value = null
        return true
    }
}