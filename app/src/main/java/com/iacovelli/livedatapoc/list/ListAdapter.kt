package com.iacovelli.livedatapoc.list

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.iacovelli.livedatapoc.R
import com.iacovelli.livedatapoc.databinding.ItemListBinding
import com.iacovelli.livedatapoc.model.ListItem

class ListAdapter(private val clickCallback: (ListItem) -> Unit): RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    var data: List<ListItem>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layout: ItemListBinding = DataBindingUtil.inflate(inflater, R.layout.item_list, parent, false)

        return ViewHolder(layout)
    }

    override fun getItemCount() = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBinding.presenter = ItemPresenter(data!![position], clickCallback)
    }

    class ViewHolder(val dataBinding: ItemListBinding): RecyclerView.ViewHolder(dataBinding.root)
}