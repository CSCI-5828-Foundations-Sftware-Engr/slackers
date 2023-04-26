package com.goodboards.app.kt



class SessionData (session_id: String, sessionName: String, gameName: String, players: List<PlayerData>) {
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

    var gameName = gameName
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