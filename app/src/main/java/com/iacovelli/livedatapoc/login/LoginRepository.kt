package com.iacovelli.livedatapoc.login

import com.iacovelli.livedatapoc.model.LoginCredential
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

class LoginRepository {
    fun <T> login(loginCredential: LoginCredential, callback: (logged: Boolean) -> T) {
        println("Logging user with credentials: $loginCredential")
        callback(true)
    }
}