package com.wardhanisukoco.newstron.modules.news.domain.models
data class ArticlesResponse(
    val status: String,
    val totalResults: String,
    val articles: List<Article>
)