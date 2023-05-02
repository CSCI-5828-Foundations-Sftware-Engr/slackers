package com.goodboards.redis

import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisCommands

class RedisInterface {
    private val redisHost = SystemWrapper.getenv("REDIS_TLS_URL") //mock sys wrapper - fake url stuff whatever
    private val redisClient: RedisClient = RedisClient.create(redisHost) // mock it for that specific url - get the Redis client object
    private val connection: StatefulRedisConnection<String, String> = redisClient.connect() // When this is called mock again
    private val redisCommands: RedisCommands<String, String> = connection.sync() //  Mockk mockk mockk - and mockk again

    fun pushToList(name: String, values: List<String>) {
        redisCommands.lpush(name, *values.toTypedArray())
    }

    fun getFromList(name: String, count: Long = 1): List<String> {
        return redisCommands.rpop(name, count)
    }

    fun close() {
        connection.close()
        redisClient.shutdown()
    }
}