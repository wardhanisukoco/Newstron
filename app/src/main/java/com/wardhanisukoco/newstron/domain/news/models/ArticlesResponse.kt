package com.wardhanisukoco.newstron.domain.news.models
data class ArticlesResponse(
    val status: String,
    val totalResults: String,
    val articles: List<Article>
)