package com.wardhanisukoco.newstron.domain.news.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wardhanisukoco.newstron.domain.news.models.Article
import com.wardhanisukoco.newstron.domain.news.repositories.NewsRepository
import com.wardhanisukoco.newstron.infrastructure.network.ApiClientListener
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TopHeadlinesViewModel: ViewModel() {
    private val repository = NewsRepository(object: ApiClientListener {
        override fun handleUnauthorized() {
            TODO("Not yet implemented")
        }
    })

    fun getArticles(): Flow<PagingData<Article>> {
        return repository.getArticles("technology")
            .cachedIn(viewModelScope)
    }
}