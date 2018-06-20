package com.iacovelli.livedatapoc.list

import com.iacovelli.livedatapoc.model.ListItem


class ItemPresenter(private val item: ListItem, private val callback: (ListItem) -> Unit) {

    val title: String
        get() = item.title

    fun onClick() {
        callback(item)
    }
}