package com.iacovelli.livedatapoc.common

import android.databinding.BindingAdapter
import android.view.View

class VisibleBinder {

    companion object {
        @BindingAdapter("visible")
        @JvmStatic fun setVisible(view: View, visible: Boolean) {
            val visibility = if (visible) {
                View.VISIBLE
            } else {
                View.GONE
            }
            view.visibility = visibility
        }
    }

}