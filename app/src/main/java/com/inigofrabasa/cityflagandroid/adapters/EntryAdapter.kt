package com.inigofrabasa.cityflagandroid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.inigofrabasa.cityflagandroid.data.model.Model
import com.inigofrabasa.cityflagandroid.databinding.ApplicationItemBinding

class EntryAdapter : RecyclerView.Adapter<EntryAdapter.ViewHolder>(){

    private var items : List<Model.Entry>? = mutableListOf()
    private var listenerApplicationSelected : ListenerApplicationSelected? = null

    fun submitList(inItems : List<Model.Entry>?){
        this.items = inItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ApplicationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        fun onViewClick() {
            items?.get(position)?.let { listenerApplicationSelected?.onClickFoundation(it) }
        }

        items?.get(position)?.let { holder.bindData(it, ::onViewClick) }
    }

    class ViewHolder (private var binding: ViewDataBinding? = null) : RecyclerView.ViewHolder(binding?.root!!) {
        fun bindData(item: Model.Entry, clickListener: () -> Unit) {
            (binding as ApplicationItemBinding).apply {
                entry = item
                content.setOnClickListener { clickListener() }
            }
        }
    }

    fun setOnClickApplicationListener(listenerApplicationSelected : ListenerApplicationSelected){
        this.listenerApplicationSelected = listenerApplicationSelected
    }

    interface ListenerApplicationSelected{
        fun onClickFoundation(application : Model.Entry)
    }
}