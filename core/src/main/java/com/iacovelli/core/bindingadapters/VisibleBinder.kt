package com.iacovelli.core.bindingadapters

import android.databinding.BindingAdapter
import android.view.View

class VisibleBinder {

    companion object {
        @BindingAdapter("visible")
        @JvmStatic fun setVisible(view: View, visible: Boolean) {
            view.visibility = GetVisibility().execute(visible)
        }
    }

}