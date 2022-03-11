package com.wardhanisukoco.newstron.domain.news.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.wardhanisukoco.newstron.domain.news.models.Article
import com.wardhanisukoco.newstron.domain.news.repository.NewsRepository
import com.wardhanisukoco.newstron.infrastructure.network.ApiClientListener
import kotlinx.coroutines.launch

class TopHeadlinesViewModel: ViewModel() {
    private var articles: List<Article> = listOf()
    private val repository = NewsRepository(object: ApiClientListener {
        override fun handleUnauthorized() {
            TODO("Not yet implemented")
        }
    })
     fun getTopHeadlines() {
        viewModelScope.launch {
            val response = repository.getTopHeadlines("technology")
            response?.let {
                articles = response.articles
            }
        }
    }
}