package com.tbse.mininews.service.model

data class Posts(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)