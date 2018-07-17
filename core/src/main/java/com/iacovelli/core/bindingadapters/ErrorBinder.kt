package com.iacovelli.core.bindingadapters

import android.databinding.BindingAdapter
import android.support.annotation.StringRes
import android.support.design.widget.TextInputLayout

class ErrorBinder {

    companion object {
        @BindingAdapter("error")
        @JvmStatic fun setError(view: TextInputLayout, @StringRes resId: Int?) {

            val errorEnabled = IsErrorEnabled().execute(resId)
            if (errorEnabled) {
                view.error = view.context.getString(resId!!)
            }
            view.isErrorEnabled = errorEnabled
        }
    }
}