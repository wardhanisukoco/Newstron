package com.wardhanisukoco.newstron.modules.news.domain.services

import com.wardhanisukoco.newstron.modules.news.domain.models.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("q") searchTerm: String? = null,
        @Query("sources") sources: String? = null,
        @Query("category") category: String? = null,
        @Query("language") language: String? = null,
        @Query("country") country: String? = "id",
        @Query("pageSize") pageSize: Int? = null,
        @Query("page") page: Int? = null
    ): Response<ArticlesResponse>
}