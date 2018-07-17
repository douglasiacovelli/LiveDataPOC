package com.iacovelli.livedatapoc.list

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iacovelli.livedatapoc.R
import com.iacovelli.livedatapoc.databinding.ListFragmentBinding
import com.iacovelli.livedatapoc.model.ListItem

class ListFragment: Fragment() {
    private lateinit var dataBinding: ListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)

        setupUI()
        return dataBinding.root
    }

    private fun setupUI() {
        val adapter = ListAdapter(onItemClicked)
        adapter.data = listOf(
                ListItem("Item 1", "This is the first item"),
                ListItem("Item 2", "This is the second item"),
                ListItem("Item 3", "This is the third item"))

        val recyclerView = dataBinding.list
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    private val onItemClicked = { item: ListItem ->
        showDialog(item)
    }

    private fun showDialog(item: ListItem) {
        context?.let {
            AlertDialog.Builder(it)
                    .setTitle(item.title)
                    .setMessage(item.description)
                    .setPositiveButton(R.string.ok) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                    .show()
        }

    }
}