package com.iacovelli.livedatapoc.login

import android.arch.lifecycle.*
import com.iacovelli.livedatapoc.R
import com.iacovelli.core.schedulers.Schedulers
import com.iacovelli.core.schedulers.SchedulersContract
import com.iacovelli.core.livedatautils.SingleEvent
import com.iacovelli.livedatapoc.model.LoginCredential

class LoginViewModel(private val repository: LoginRepository,
                     val formValidator: LoginFormValidator,
                     private val schedulers: SchedulersContract = Schedulers()): ViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val userLogged = MutableLiveData<SingleEvent<Boolean>>()
    val message = MutableLiveData<SingleEvent<Int>>()
    val loading = MutableLiveData<Boolean>()
    val buttonEnabled: LiveData<Boolean>

    init {
        buttonEnabled = Transformations.map(loading) {
            !it
        }
        loading.value = false
    }

    fun onUserSubmit() {
        if (formValidator.isValid(email.value, password.value)) {
            authenticateUser()
        } else {
            message.value = SingleEvent(R.string.invalid_form)
        }
    }

    private fun authenticateUser() {
        loading.value = true

        val loginCredential = LoginCredential(email.value!!, password.value!!)
        repository.login(loginCredential)
                .subscribeOn(schedulers.io)
                .observeOn(schedulers.main)
                .doAfterTerminate {
                    loading.value = false
                }
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