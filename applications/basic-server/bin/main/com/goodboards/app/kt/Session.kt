package com.goodboards.app.kt

import java.util.concurrent.atomic.AtomicInteger

class Session(var sessionId: String, var sessionName: String, var players: List<Player>, var game_id: String) {



    companion object {
        fun newEntry(sessionId: String, sessionName: String, players: List<Player>, game_id: String) = Session(sessionId, sessionName, players, game_id)
    }
}
