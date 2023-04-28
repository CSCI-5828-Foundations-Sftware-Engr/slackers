package com.goodboards.redis

import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisCommands

class RedisInterface {
    private val REDIS_TLS_URI = SystemWrapper.getenv("REDIS_TLS_URI")
    private val redisClient: RedisClient = RedisClient.create(REDIS_TLS_URI)
    private val connection: StatefulRedisConnection<String, String> = redisClient.connect()
    private val redisCommands: RedisCommands<String, String> = connection.sync()

    fun pushToList(key: String, vararg values: String) {
        redisCommands.lpush(key, *values)
    }

    fun getFromList(key: String, count: Long = 1): List<String> {
        return redisCommands.rpop(key, count)
    }

    // TODO Object

    fun close() {
        connection.close()
        redisClient.shutdown()
    }
}
