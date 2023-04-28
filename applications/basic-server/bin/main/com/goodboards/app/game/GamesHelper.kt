package com.goodboards.app.game

import com.goodboards.app.database.DBHelper
import com.goodboards.db.DBConnection
import com.goodboards.db.DBInterface

class GamesHelper {
    companion object {
        fun getAllGames(): List<Game> {
           val dbGames = DBHelper.getDBInterface().getAllGames()
            return dbGames.map { dbgame-> Game(dbgame.uuid, dbgame.name, dbgame.description) }

        }
    }
}