package com.wardhanisukoco.newstron.domain.news.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.DiffUtil
import com.squareup.picasso.Picasso
import com.wardhanisukoco.newstron.R
import com.wardhanisukoco.newstron.application.adapter.BasePagingAdapter
import com.wardhanisukoco.newstron.application.adapter.LoadingStateAdapter
import com.wardhanisukoco.newstron.application.adapter.viewholder.BaseViewHolder
import com.wardhanisukoco.newstron.databinding.ItemHeadlinesBinding
import com.wardhanisukoco.newstron.domain.news.models.Article
import androidx.viewbinding.ViewBinding


class TopHeadlinesListAdapter(private val context: Context, val listener: OnItemClickListener)
    : BasePagingAdapter<Article, ItemHeadlinesBinding>(DiffCallBack()),
    LoadingStateAdapter.RetryListener {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemHeadlinesBinding> {
        return ViewHolder(
            ItemHeadlinesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun bind(binding: ItemHeadlinesBinding, item: Article) {
        binding.title.text = item.title
        binding.desc.text = item.description
        Picasso.with(context)
            .load(item.urlToImage)
            .placeholder(R.drawable.bg_gradient)
            .fit()
            .into(binding.image)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemHeadlinesBinding>, position: Int) {
        val params = LinearLayoutCompat.LayoutParams(
            LinearLayoutCompat.LayoutParams.MATCH_PARENT,
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        )
        if (position == 0) {
            val containerParams = FrameLayout.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT
            )
            holder.binder.container.layoutParams = containerParams
            params.weight = .618f
            holder.binder.container.orientation = LinearLayoutCompat.VERTICAL
        } else {
            params.weight = 1.618f
            holder.binder.container.orientation = LinearLayoutCompat.HORIZONTAL
        }
        holder.binder.imageContainer.layoutParams = params

        super.onBindViewHolder(holder, position)
    }
    inner class ViewHolder<T: ViewBinding>(binder: T): BaseViewHolder<T>(binder) {
        init {
            binder.root.setOnClickListener {
                listener.onItemClick(getItem(absoluteAdapterPosition))
            }
        }
    }
    interface OnItemClickListener {
        fun onItemClick(item: Article?)
    }
    internal class DiffCallBack : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}