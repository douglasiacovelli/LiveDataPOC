package com.iacovelli.livedatapoc

import com.iacovelli.core.schedulers.SchedulersContract
import io.reactivex.*
import io.reactivex.schedulers.Schedulers

class FakeSchedulers: SchedulersContract {

    override val io: Scheduler
        get() = Schedulers.trampoline()

    override val main: Scheduler
        get() = Schedulers.trampoline()
}