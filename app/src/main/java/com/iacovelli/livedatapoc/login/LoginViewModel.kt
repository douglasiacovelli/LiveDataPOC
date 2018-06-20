package com.iacovelli.livedatapoc.login

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.iacovelli.livedatapoc.R
import com.iacovelli.livedatapoc.common.Event
import com.iacovelli.livedatapoc.model.LoginCredential

class LoginViewModel(private val repository: LoginRepository,
                     val formValidator: LoginFormValidator): ViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val userLogged = MutableLiveData<Event<Boolean>>()
    val message = MutableLiveData<Event<Int>>()

    fun onUserSubmit() {
        if (formValidator.isValid(email.value, password.value)) {
            authenticateUser()
        } else {
            message.value = Event(R.string.invalid_form)
        }
    }

    private fun authenticateUser() {
        val loginCredential = LoginCredential(email.value!!, password.value!!)
        repository.login(loginCredential) {
            if (it) {
                userLogged.value = Event(true)
            } else {
                message.value = Event(R.string.unexpected_error)
            }
        }
    }

    class Factory: ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(LoginRepository(), LoginFormValidator()) as T
        }

    }
}