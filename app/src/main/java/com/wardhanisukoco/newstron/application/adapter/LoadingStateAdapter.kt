package com.wardhanisukoco.newstron.application.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wardhanisukoco.newstron.databinding.LayoutLoadingStateBinding

class LoadingStateAdapter(
    private val listener: RetryListener
) : LoadStateAdapter<LoadingStateAdapter.NetworkStateItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        NetworkStateItemViewHolder(
            LayoutLoadingStateBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ) { listener.retry() }

    private fun bind(binding: LayoutLoadingStateBinding, loadState: LoadState) {
        with(binding) {
            progressBar.isVisible = loadState is LoadState.Loading
            retryButton.isVisible = loadState is LoadState.Error
            errorMsg.isVisible =
                !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
            errorMsg.text = (loadState as? LoadState.Error)?.error?.message
        }
    }

    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) =
        bind(holder.binding, loadState)

    class NetworkStateItemViewHolder(
        internal val binding: LayoutLoadingStateBinding,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.retryButton.setOnClickListener { retryCallback() }
        }
    }

    interface RetryListener {
        fun retry()
    }
}