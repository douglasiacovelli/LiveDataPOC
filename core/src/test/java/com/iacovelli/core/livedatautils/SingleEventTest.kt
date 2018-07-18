package com.iacovelli.core.livedatautils

import org.junit.Test

import org.junit.Assert.*

class SingleEventTest {

    @Test
    fun shouldReturnContentSetWhenFirstTime() {
        val string = "test"
        val event = SingleEvent(string)
        assertEquals(string, event.getContent())
    }

    @Test
    fun shouldReturnNullIfContentIsAlreadyConsumed() {
        val string = "test"
        val event = SingleEvent(string)
        event.getContent()
        assertNull(event.getContent())
    }
}