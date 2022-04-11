package com.wardhanisukoco.newstron.modules.news.domain.models
data class ApiError(
    val status: String,
    val code: String,
    val message: String
)