package com.iacovelli.livedatapoc.login

import com.iacovelli.livedatapoc.model.LoginCredential
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import java.util.concurrent.TimeUnit

class LoginRepository {

    fun login(loginCredential: LoginCredential): Completable {
        println("Logging user with credentials: $loginCredential")
        return Completable.create {
            Thread.sleep(2000)
            it.onComplete()
        }
    }
}