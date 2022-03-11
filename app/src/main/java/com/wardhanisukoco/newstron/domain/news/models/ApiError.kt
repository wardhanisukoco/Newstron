package com.wardhanisukoco.newstron.domain.news.models
data class ApiError(
    val status: String,
    val code: String,
    val message: String
)