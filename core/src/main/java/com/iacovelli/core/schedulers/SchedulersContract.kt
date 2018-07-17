package com.iacovelli.core.schedulers

import io.reactivex.Scheduler

interface SchedulersContract {
    val io: Scheduler
    val main: Scheduler
}