package com.iacovelli.core.bindingadapters

import android.view.View

class GetVisibility {
    fun execute(visible: Boolean): Int {
        return if (visible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

}