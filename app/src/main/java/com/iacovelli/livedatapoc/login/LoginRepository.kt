package com.iacovelli.livedatapoc.login

import com.iacovelli.livedatapoc.model.LoginCredential
import io.reactivex.Completable

class LoginRepository {

    fun login(loginCredential: LoginCredential): Completable {
        println("Logging user with credentials: $loginCredential")
        return Completable.create {
            Thread.sleep(2000)
            it.onComplete()
        }
    }
}