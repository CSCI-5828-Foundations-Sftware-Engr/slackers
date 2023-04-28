package com.goodboards.app.kt

class PlayerGameOptions(players: List<Player>, games: List<Game>) {
    var players = players
        get() = field
        set(value) {
            field = value
        }

    var games = games
        get() = field
        set(value) {
            field = value
        }
}