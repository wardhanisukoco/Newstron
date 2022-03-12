package com.wardhanisukoco.newstron.application.adapter

import android.annotation.SuppressLint
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.wardhanisukoco.newstron.application.adapter.viewholder.BaseViewHolder

abstract class BaseAdapter<BINDING : ViewBinding, T : Any>(
    var data: List<T>
) : RecyclerView.Adapter<BaseViewHolder<BINDING>>() {

    abstract fun bind(binding: BINDING, item: T)
    abstract fun retry()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(list: List<T>) {
        data = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BINDING>, position: Int) {
        bind(holder.binder, data[position])
    }

    override fun getItemCount(): Int = data.size
}