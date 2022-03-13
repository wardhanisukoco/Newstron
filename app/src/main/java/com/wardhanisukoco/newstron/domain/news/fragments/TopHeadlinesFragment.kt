package com.wardhanisukoco.newstron.domain.news.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.wardhanisukoco.newstron.application.adapter.LoadingStateAdapter
import com.wardhanisukoco.newstron.databinding.FragmentTopHeadlinesBinding
import com.wardhanisukoco.newstron.domain.news.adapters.TopHeadlinesListAdapter
import com.wardhanisukoco.newstron.domain.news.models.Article
import com.wardhanisukoco.newstron.domain.news.viewmodels.TopHeadlinesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TopHeadlinesFragment : Fragment(), TopHeadlinesListAdapter.OnItemClickListener {

    private var _adapter: TopHeadlinesListAdapter? = null
    private var _binding: FragmentTopHeadlinesBinding? = null
    private val binding: FragmentTopHeadlinesBinding
        get() = _binding!!


    private val viewModel: TopHeadlinesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentTopHeadlinesBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        collectUiState()

        viewModel.getArticles()
    }

    override fun onItemClick(item: Article?) {
        item?.let { NewsDetailFragment(it).show(activity!!.supportFragmentManager, "") }
    }

    private fun setupView() {
        _adapter = TopHeadlinesListAdapter(activity!!.applicationContext, this)
        _adapter?.withLoadStateHeaderAndFooter(
            header = LoadingStateAdapter(_adapter!!),
            footer = LoadingStateAdapter(_adapter!!)
        )
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity!!.applicationContext)
            setHasFixedSize(true)

            adapter = _adapter
        }
    }

    private fun collectUiState() {
        lifecycleScope.launch {
            viewModel.getArticles().collectLatest { articles ->
                _adapter?.submitData(articles)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _adapter = null
    }
}