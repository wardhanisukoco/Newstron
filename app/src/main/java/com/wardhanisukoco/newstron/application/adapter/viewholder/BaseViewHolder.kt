package com.wardhanisukoco.newstron.application.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BaseViewHolder<BINDING : ViewBinding>(val binder: BINDING) :
    RecyclerView.ViewHolder(binder.root) {
}