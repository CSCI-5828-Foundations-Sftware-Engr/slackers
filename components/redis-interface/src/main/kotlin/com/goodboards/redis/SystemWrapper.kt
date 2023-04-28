package com.goodboards.redis

object SystemWrapper {

    fun getenv(key: String): String = System.getenv(key)!!

}