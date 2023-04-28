package com.goodboards.db

object SystemWrapper {

    fun getenv(key: String): String = System.getenv(key)!!

}