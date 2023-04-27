package com.goodboards.app.news

import com.goodboards.app.database.DBHelper

class NewsHelper {
    companion object {
        fun getNewsForGame(gameName: String): List<News> {
            val dbNews = DBHelper.getDBInterface().getNewsForGame(gameName)
            return dbNews.map { dbnews-> News(dbnews.uuid, dbnews.author, dbnews.title, dbnews.url, dbnews.urlToImage, dbnews.content) }

        }
    }
}