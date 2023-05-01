# Redis
Our system uses Redis to load and cache news and games 
that are fetched from the internet or the database. The Redis
package is defined in com.goodboards.redis which is 
defined in the redis-interface component.


## Quick overview of Redis Use in goodboards
We use two Redis lists, Games and News to push and cache
records as needed and use a rpop (right pop) to retrieve
elements from the list.

## Local Setup
### Installation
1. If you have pip installed you can use `pip install redis`
to install Redis on you computer.
2. Start redis server by using `redis-server` in terminal.
3. Once the server has started, open a new terminal tab and
enter redis-cli, enter the command `PING` and Redis will
respond with `PONG` confirming the server has started.
4. That's it, installation is complete :D

### Environment Variables
1. The Goodboards app will access redis through a
environment (env) variable called `REDIS_TLS_URL`, so for Redis
to work properly you will need to set the env variable. On
Mac, \
you can use `export REDIS_TLS_URL=redis://localhost:6379`
to confirm the variable has been set use `echo $REDIS_TLS_URL`
2. And that's it ;D

## Redis Package Contents
### Main Components
* `RedisInterface` - Class for accessing Redis Queue
### Utilities
* `SystemWrapper` - Contains utility functions to access
env variables.

### Class Methods & Properties
#### Properties
```Kotlin
// Get REDIS_TLS_URL env variable value   
private val redisHost = SystemWrapper.getenv("REDIS_TLS_URL")
// Create a redis client for the given host url
private val redisClient: RedisClient = RedisClient.create(redisHost)
// Connect the client to redis server at the host url, creating a connection
private val connection: StatefulRedisConnection<String, String> = redisClient.connect()
// connection.sync returns a RedisCommands instance that us to synchronously execute Redis command over the connection
private val redisCommands: RedisCommands<String, String> = connection.sync()
```
#### Methods

```Kotlin
fun pushToList(name: String, vararg values: String) {
    redisCommands.lpush(name, *values)
}
```
This function takes two arguments, a name and a variable
length array of string values. Then it push these string
to the start or left the list named 'name' on the Redis
server. If the there is no list with that name then a new
list is created with that name.

```Kotlin
fun getFromList(name: String, count: Long = 1): List<String> {
    return redisCommands.rpop(name, count)
}
```
This method takes two arguments name (name of the list)
and count (number of elements to get from the right side
of the list). The default value of count is set to 1.

```Kotlin
fun close() {
    connection.close()
    redisClient.shutdown()
}
```
This method is used to close the Redis connection and shutdown the Redis client. It does not take any parameters.

#### *Example*
```Kotlin
import com.goodboards.redis.*
val redisInterface = RedisInterface()
redisInterface.lpush("Games", "{name: 'Chess', desc:'Good Game'}")
println(redisInterface.rpop("Games"))
// prints -> "{name: 'Chess', desc:'Good Game'}"
```