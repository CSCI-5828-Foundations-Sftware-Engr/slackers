package com.goodboards.app.collector

import com.goodboards.db.DBConnection
import com.goodboards.db.DBInterface
import com.goodboards.redis.RedisInterface

object Wrapper {

    // System.getenv()
    fun getEnv(key: String): String = System.getenv(key)!!

    // Redis connection
    fun getRedisInterface(): RedisInterface = RedisInterface()

    // DB connection
    fun getDBInterface(): DBInterface {
        val dbCreds = DBConnection.getCredentials()
        DBConnection.setConnection(dbCreds.url, dbCreds.username, dbCreds.password)
        return DBInterface(DBConnection)
    }

    // HttpClient
    fun getHttpClient(): HttpClientWrapper {
        return HttpClientWrapper()
    }
}