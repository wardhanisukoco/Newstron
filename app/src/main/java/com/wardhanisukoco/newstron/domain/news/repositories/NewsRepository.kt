package com.wardhanisukoco.newstron.domain.news.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wardhanisukoco.newstron.application.adapter.BasePagingSource.Companion.NETWORK_PAGE_SIZE
import com.wardhanisukoco.newstron.domain.news.models.Article
import com.wardhanisukoco.newstron.domain.news.services.NewsApiService
import com.wardhanisukoco.newstron.infrastructure.network.ApiClient
import com.wardhanisukoco.newstron.infrastructure.network.ApiClientListener
import kotlinx.coroutines.flow.Flow

class NewsRepository(listener: ApiClientListener) {
    private val apiClient by lazy { ApiClient(listener).getClient() }
    private val apiService: NewsApiService = apiClient.create(NewsApiService::class.java)

    fun getArticles(category: String? = null): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                TopHeadlinesPagingSource(service = apiService, category = category)
            }
        ).flow
    }
}
