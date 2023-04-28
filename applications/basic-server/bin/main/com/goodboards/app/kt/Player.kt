package com.goodboards.app.kt
import java.util.concurrent.atomic.AtomicInteger

class Player(val id: Int, var name: String) {
    companion object {
        private val idCounter = AtomicInteger()
    }
}