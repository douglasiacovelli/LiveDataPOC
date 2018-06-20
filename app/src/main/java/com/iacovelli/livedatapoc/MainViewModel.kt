package com.iacovelli.livedatapoc

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.iacovelli.livedatapoc.common.Event

class MainViewModel: ViewModel() {
    val nextScreen = MutableLiveData<Event<Screen>>()

    fun goToScreen(screen: Screen) {
        nextScreen.value = Event(screen)
    }
}

enum class Screen {
    LOGIN,
    LIST
}