package com.goodboards.app.kt

class PlayerData(playerName: String, wins: Int = 0, loss: Int = 0) {
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

    var playerName = playerName
        get() = field
        set(value) {
            field = value
        }

}