package com.inigofrabasa.cityflagandroid.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
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

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, images : List<Model.Image>?) {
    if (!images.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(images[0].label)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("textView")
fun bindTextView(textView: TextView, text: String?) {
    text?.apply{
        textView.text = text
    }
}

@BindingAdapter("viewVisibility")
fun bindViewVisibility(view: View, value: Int ) {
    when(value){
        0 -> view.visibility = View.VISIBLE
        8 -> view.visibility = View.GONE
    }
}