package com.iacovelli.core

import android.view.View
import com.iacovelli.core.bindingadapters.GetVisibility
import org.junit.Test

import org.junit.Assert.*

class GetVisibilityTest {

    @Test
    fun shouldReturnVisibleIfTrue() {
        assertEquals(View.VISIBLE, GetVisibility().execute(true))
    }

    @Test
    fun shouldReturnGoneIfFalse() {
        assertEquals(View.GONE, GetVisibility().execute(false))
    }
}