package com.goodboards.db

import java.sql.Connection

data class DBCredentials(val url: String, val username: String, val password: String)

object DBConnection {
    private var connection: Connection? = null

    fun getCredentials(): DBCredentials {
        val jdbcUrl: String = SystemWrapper.getenv("JDBC_DATABASE_URL")
        val username: String = SystemWrapper.getenv("DATABASE_USERNAME")
        val password: String = SystemWrapper.getenv("DATABASE_PASSWORD")
        return DBCredentials(jdbcUrl, username, password)
    }

    fun hasConnection(): Boolean {
        return connection != null
    }

    fun getConnection(): Connection {
        return connection!!
    }

    fun setConnection(jdbcUrl: String, username: String, password: String) {
        connection = DriverManagerWrapper
            .getConnection(jdbcUrl, username, password)
    }
}