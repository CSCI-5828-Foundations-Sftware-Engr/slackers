package com.goodboards.app.game


import com.goodboards.app.database.DBHelper

class GamesHelper {
    companion object {
        fun getAllGames(): List<Game> {
           val dbGames = DBHelper.getDBInterface().getAllGames()
            return dbGames.map { dbgame-> Game(dbgame.uuid, dbgame.name, dbgame.description) }
        }
        fun getGameById(id: String): Game {
            val dbgame = DBHelper.getDBInterface().getGameById(id);
            return Game(dbgame.uuid, dbgame.name, dbgame.description)
        }
    }
}