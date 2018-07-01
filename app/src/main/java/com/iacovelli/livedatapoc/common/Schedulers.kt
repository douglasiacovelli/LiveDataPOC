package com.iacovelli.livedatapoc.common

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Schedulers: SchedulersContract {
    override val io: Scheduler
        by lazy {
            Schedulers.io()
        }

    override val main: Scheduler
        by lazy {
            AndroidSchedulers.mainThread()
        }

}