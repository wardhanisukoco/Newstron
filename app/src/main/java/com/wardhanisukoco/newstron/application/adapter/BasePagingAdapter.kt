package com.wardhanisukoco.newstron.application.adapter

import android.annotation.SuppressLint
import androidx.annotation.LayoutRes
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.wardhanisukoco.newstron.application.adapter.viewholder.BaseViewHolder

abstract class BasePagingAdapter<T : Any, BINDING : ViewBinding>(diffCallback: DiffUtil.ItemCallback<T>) : PagingDataAdapter<T, BaseViewHolder<BINDING>>(
    diffCallback
) {

    abstract fun bind(binding: BINDING, item: T)

    override fun onBindViewHolder(holder: BaseViewHolder<BINDING>, position: Int) {
        getItem(position)?.let { bind(holder.binder, it) }
    }
}