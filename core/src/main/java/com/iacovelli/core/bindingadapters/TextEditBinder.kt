package com.iacovelli.core.bindingadapters

import android.databinding.BindingAdapter
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class TextEditBinder {
    companion object {

        @JvmStatic
        @BindingAdapter(value = ["formatter", "validator"], requireAll = false)
        fun addListeners(editText: EditText, formatter: Formatter?, validator: Validator?){

            editText.addTextChangedListener(object: TextWatcher {
                var editingText = false

                override fun afterTextChanged(s: Editable?) {
                    if (!editingText && formatter != null) {
                        editingText = true
                        val formattedText = formatter.format(s.toString())
                        editText.setText(formattedText)
                        editText.setSelection(formattedText.length)
                        editingText = false
                    }

                    validator?.let {
                        validator.validate(editText.text.toString())
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

interface Validator {
    fun validate(string: String)
}