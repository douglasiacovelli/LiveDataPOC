package com.iacovelli.livedatapoc.common

class Event<T>(private val content: T) {
    private var contentConsumed = false

    fun getContent(): T? {
        return if (!contentConsumed) {
            contentConsumed = true
            content
        } else {
            null
        }
    }
}