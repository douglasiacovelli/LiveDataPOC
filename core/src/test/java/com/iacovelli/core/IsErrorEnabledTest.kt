package com.iacovelli.core

import com.iacovelli.core.bindingadapters.IsErrorEnabled
import org.junit.Test

import org.junit.Assert.*

class IsErrorEnabledTest {

    @Test
    fun shouldReturnTrueIfNotNull() {
        assertTrue(IsErrorEnabled().execute(1))
    }

    @Test
    fun shouldReturnFalseIfNull() {
        assertFalse(IsErrorEnabled().execute(null))
    }
}