package com.goodboards.app.collector

import kotlinx.serialization.Serializable

@Serializable
data class Articles(
    val source: Source,
    val author: String = "",
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val publishedAt: String = "",
    val content: String = ""
)
@Serializable
data class Source(
    val id: String = "",
    val name: String = ""
)

@Serializable
data class NewsResponse(val status: String, val totalResults: Int, val articles: List<Articles>)

@Serializable
data class NewsUnit(val gameID: String, val title: String, val description: String, val url: String)

class News
private constructor(articles: List<Articles>?)
