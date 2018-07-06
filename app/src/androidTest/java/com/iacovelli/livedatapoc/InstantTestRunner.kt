package com.iacovelli.livedatapoc

import android.support.test.runner.AndroidJUnitRunner
import com.squareup.rx2.idler.Rx2Idler
import io.reactivex.plugins.RxJavaPlugins

class InstantTestRunner: AndroidJUnitRunner() {

    override fun onStart() {

        RxJavaPlugins.setInitNewThreadSchedulerHandler(
                Rx2Idler.create("RxJava 2.x Computation New Thread"))
        RxJavaPlugins.setInitComputationSchedulerHandler(
                Rx2Idler.create("RxJava 2.x Computation Scheduler"))
        RxJavaPlugins.setInitIoSchedulerHandler(
                Rx2Idler.create("RxJava 2.x IO Scheduler"))

        super.onStart()
    }
}