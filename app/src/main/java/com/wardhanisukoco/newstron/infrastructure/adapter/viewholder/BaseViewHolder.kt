package com.wardhanisukoco.newstron.infrastructure.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<BINDING : ViewBinding>(val binder: BINDING) :
    RecyclerView.ViewHolder(binder.root) {
}