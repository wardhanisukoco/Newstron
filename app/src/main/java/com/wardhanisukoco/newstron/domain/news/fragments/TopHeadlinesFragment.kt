package com.wardhanisukoco.newstron.domain.news.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.wardhanisukoco.newstron.databinding.FragmentTopHeadlinesBinding
import com.wardhanisukoco.newstron.domain.news.repository.NewsRepository
import com.wardhanisukoco.newstron.domain.news.viewmodel.TopHeadlinesViewModel
import com.wardhanisukoco.newstron.infrastructure.network.ApiClientListener
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TopHeadlinesFragment : Fragment() {

    private lateinit var binding: FragmentTopHeadlinesBinding

    private val viewModel: TopHeadlinesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTopHeadlinesBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTopHeadlines()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}