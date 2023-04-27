package com.goodboards.app.util

object EnvHelper {
    fun getEnv(key: String): String = System.getenv(key)!!
}