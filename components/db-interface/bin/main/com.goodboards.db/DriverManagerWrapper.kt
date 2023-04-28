package com.goodboards.db

import java.sql.Connection
import java.sql.DriverManager

object DriverManagerWrapper {

    fun getConnection(jdbcUrl: String, username: String, password: String): Connection? {
        return DriverManager.getConnection(jdbcUrl, username, password)
    }

}