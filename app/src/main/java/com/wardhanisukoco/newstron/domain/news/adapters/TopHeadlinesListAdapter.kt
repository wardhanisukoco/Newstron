package com.wardhanisukoco.newstron.domain.news.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.DiffUtil
import com.squareup.picasso.Picasso
import com.wardhanisukoco.newstron.R
import com.wardhanisukoco.newstron.application.adapter.BasePagingAdapter
import com.wardhanisukoco.newstron.application.adapter.LoadingStateAdapter
import com.wardhanisukoco.newstron.application.adapter.viewholder.BaseViewHolder
import com.wardhanisukoco.newstron.databinding.ItemHeadlinesBinding
import com.wardhanisukoco.newstron.domain.news.models.Article
import android.widget.LinearLayout




class TopHeadlinesListAdapter(private val context: Context) : BasePagingAdapter<Article, ItemHeadlinesBinding>(DiffCallBack()),
    LoadingStateAdapter.RetryListener {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemHeadlinesBinding> {
        return BaseViewHolder(
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
            .placeholder(R.drawable.ic_launcher_background)
            .fit()
            .into(binding.image)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemHeadlinesBinding>, position: Int) {
        val params = LinearLayoutCompat.LayoutParams(
            LinearLayoutCompat.LayoutParams.MATCH_PARENT,
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        )
        if (position == 0) {
            params.weight = .618f
            holder.binder.container.orientation = LinearLayoutCompat.VERTICAL
        } else {
            params.weight = 1.618f
            holder.binder.container.orientation = LinearLayoutCompat.HORIZONTAL
        }
        holder.binder.imageContainer.layoutParams = params

        super.onBindViewHolder(holder, position)
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