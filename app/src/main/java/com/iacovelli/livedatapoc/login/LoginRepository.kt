package com.iacovelli.livedatapoc.login

import com.iacovelli.livedatapoc.model.LoginCredential
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

class LoginRepository {
    fun login(loginCredential: LoginCredential): Boolean {
        println("Logging user with credentials: $loginCredential")
        return true
    }
}