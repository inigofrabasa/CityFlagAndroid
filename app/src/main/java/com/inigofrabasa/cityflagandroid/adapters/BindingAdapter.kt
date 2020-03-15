package com.inigofrabasa.cityflagandroid.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.inigofrabasa.cityflagandroid.data.model.Model

@BindingAdapter("listEntry")
fun bindListEntry(recyclerView: RecyclerView?, data: List<Model.Entry>?) {
    if (recyclerView != null && recyclerView.adapter != null) {
        val adapter = recyclerView.adapter as EntryAdapter
        adapter.let {
            adapter.submitList(data)
        }
    }
}
