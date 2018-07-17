package com.iacovelli.core.bindingadapters

class IsErrorEnabled {
    fun execute(resId: Int?): Boolean {
        return resId != null
    }

}
