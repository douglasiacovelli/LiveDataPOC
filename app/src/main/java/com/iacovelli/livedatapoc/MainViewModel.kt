package com.iacovelli.livedatapoc

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.iacovelli.core.livedatautils.SingleEvent

class MainViewModel: ViewModel() {
    val nextScreen = MutableLiveData<SingleEvent<Screen>>()

    fun goToScreen(screen: Screen) {
        nextScreen.value = SingleEvent(screen)
    }
}

enum class Screen {
    LOGIN,
    LIST
}