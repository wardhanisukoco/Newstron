package com.wardhanisukoco.newstron.infrastructure.adapter

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.wardhanisukoco.newstron.infrastructure.adapter.viewholder.BaseViewHolder

abstract class BasePagingAdapter<T : Any, BINDING : ViewBinding>(diffCallback: DiffUtil.ItemCallback<T>) : PagingDataAdapter<T, BaseViewHolder<BINDING>>(
    diffCallback
) {

    abstract fun bind(binding: BINDING, item: T)

    override fun onBindViewHolder(holder: BaseViewHolder<BINDING>, position: Int) {
        getItem(position)?.let { bind(holder.binder, it) }
    }
}