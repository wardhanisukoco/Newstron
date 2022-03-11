package com.wardhanisukoco.newstron.domain.news.repository

import com.wardhanisukoco.newstron.domain.news.models.ArticlesResponse
import com.wardhanisukoco.newstron.domain.news.services.NewsApiService
import com.wardhanisukoco.newstron.infrastructure.network.ApiClient
import com.wardhanisukoco.newstron.infrastructure.network.ApiClientListener
import com.wardhanisukoco.newstron.infrastructure.network.ApiRequest

class NewsRepository(listener: ApiClientListener) {
    private val apiClient by lazy { ApiClient(listener).getClient() }
    private val apiService: NewsApiService = apiClient.create(NewsApiService::class.java)

    suspend fun getTopHeadlines(category: String? = null): ArticlesResponse? {
        val request = ApiRequest.safeApiCall { apiService.getTopHeadlines(category = category) }
        if (!request.isSuccessful) {
            return null
        }
        return request.body
    }
}
