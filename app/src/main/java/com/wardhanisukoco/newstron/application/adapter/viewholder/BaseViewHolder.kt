package com.wardhanisukoco.newstron.application.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class BaseViewHolder<BINDING : ViewBinding>(val binder: BINDING) :
    RecyclerView.ViewHolder(binder.root) {
}