package com.iacovelli.core.bindingadapters

import android.databinding.BindingAdapter
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class TextEditBinder {
    companion object {

        @JvmStatic
        @BindingAdapter("formatter")
        fun addFormatter(editText: EditText, formatter: Formatter){

            editText.addTextChangedListener(object: TextWatcher {
                var edittingText = false

                override fun afterTextChanged(s: Editable?) {
                    if (!edittingText) {
                        edittingText = true
                        val formattedText = formatter.format(s.toString())
                        editText.setText(formattedText)
                        editText.setSelection(formattedText.length)
                        edittingText = false
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })
        }
    }
}

interface Formatter {
    fun format(text: String): String
}