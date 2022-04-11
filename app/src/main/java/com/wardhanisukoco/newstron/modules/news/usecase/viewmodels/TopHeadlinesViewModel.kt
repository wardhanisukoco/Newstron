package com.wardhanisukoco.newstron.modules.news.usecase.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.wardhanisukoco.newstron.modules.news.domain.models.Article
import com.wardhanisukoco.newstron.modules.news.domain.repositories.NewsRepository
import com.wardhanisukoco.newstron.infrastructure.network.ApiClientListener
import kotlinx.coroutines.flow.Flow

class TopHeadlinesViewModel: ViewModel() {
    private val repository = NewsRepository(object: ApiClientListener {
        override fun handleUnauthorized() {
        }
    })

    fun getArticles(): Flow<PagingData<Article>> {
        return repository.getArticles("technology")
//            .cachedIn(viewModelScope)
    }
}