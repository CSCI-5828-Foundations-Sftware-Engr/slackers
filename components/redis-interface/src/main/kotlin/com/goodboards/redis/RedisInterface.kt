package com.goodboards.redis

import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisCommands

class RedisInterface {
    private val redisHost = SystemWrapper.getenv("REDIS_TLS_URL")
    private val redisClient: RedisClient = RedisClient.create(redisHost)
    private val connection: StatefulRedisConnection<String, String> = redisClient.connect()
    private val redisCommands: RedisCommands<String, String> = connection.sync()

    fun pushToList(key: String, vararg values: String) {
        redisCommands.lpush(key, *values)
    }

    fun getFromList(key: String, count: Long = 1): List<String> {
        return redisCommands.rpop(key, count)
    }

    // TODO interface as Object or Class

    fun close() {
        connection.close()
        redisClient.shutdown()
    }
}
