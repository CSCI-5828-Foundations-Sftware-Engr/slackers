package com.goodboards.app.kt

class PlayerData(playerId: String, wins: Int = 0, loss: Int = 0) {
    var wins = wins
        get() = field
        set(value) {
            field = value
        }

    var loss = loss
        get() = field
        set(value) {
            field = value
        }

    var playerId = playerId
        get() = field
        set(value) {
            field = value
        }

}

class SessionData (session_id: String, sessionName: String, game_id: String, players: List<PlayerData>) {
    var session_id = session_id
        get() = field
        set(value) {
            field = value
        }

    var sessionName = sessionName
        get() = field
        set(value) {
            field = value
        }

    var game_id = game_id
        get() = field
        set(value) {
            field = value
        }

    var players = players
        get() = field
        set(value) {
            field = value
        }
}

/*
{
    "SessionData" : {
        "session_id" : "1",
        "game_id" : "1",
        "players": [{
            "khaled": {
                "playerId": "1",
                "wins": 1,
                "loss": 0
            },
            "Charles": {
                "playerId": "2",
                "wins": 0,
                "loss": 1
            }
        }]
    }
}

 */