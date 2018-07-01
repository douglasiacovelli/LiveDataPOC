package com.iacovelli.livedatapoc.login

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.iacovelli.livedatapoc.R
import com.iacovelli.livedatapoc.common.Schedulers
import com.iacovelli.livedatapoc.common.SchedulersContract
import com.iacovelli.livedatapoc.common.SingleEvent
import com.iacovelli.livedatapoc.model.LoginCredential
import io.reactivex.android.schedulers.AndroidSchedulers

class LoginViewModel(private val repository: LoginRepository,
                     val formValidator: LoginFormValidator,
                     val schedulers: SchedulersContract = Schedulers()): ViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val userLogged = MutableLiveData<SingleEvent<Boolean>>()
    val message = MutableLiveData<SingleEvent<Int>>()

    fun onUserSubmit() {
        if (formValidator.isValid(email.value, password.value)) {
            authenticateUser()
        } else {
            message.value = SingleEvent(R.string.invalid_form)
        }
    }

    private fun authenticateUser() {
        val loginCredential = LoginCredential(email.value!!, password.value!!)
        repository.login(loginCredential)
                .subscribeOn(schedulers.io)
                .observeOn(schedulers.main)
                .subscribe({
                    userLogged.value = SingleEvent(true)
                }, {
                    message.value = SingleEvent(R.string.unexpected_error)
                })
    }

    class Factory: ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(LoginRepository(), LoginFormValidator()) as T
        }
    }
}