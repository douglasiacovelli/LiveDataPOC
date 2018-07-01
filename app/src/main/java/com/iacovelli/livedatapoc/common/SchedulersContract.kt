package com.iacovelli.livedatapoc.common

import io.reactivex.Scheduler

interface SchedulersContract {
    val io: Scheduler
    val main: Scheduler
}