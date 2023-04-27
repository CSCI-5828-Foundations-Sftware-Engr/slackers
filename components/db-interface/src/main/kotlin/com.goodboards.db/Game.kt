package com.goodboards.db

data class Game(val uuid: String, val name: String, val description: String)

data class News(val uuid: String, val author: String, val title: String, val url: String, val urlToImage: String,
           val content: String )