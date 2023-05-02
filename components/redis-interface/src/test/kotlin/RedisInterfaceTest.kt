package test.goodboards.redis

import com.goodboards.redis.RedisInterface
import org.junit.Test
import io.mockk.*
import kotlin.test.assertEquals

class RedisInterfaceTest {
    @Test
    fun testPushOneToList(){
        val redisInterface = mockk<RedisInterface>(relaxed = true)
        val listName: String = "Games"
        val game = listOf<String>("{name: \"chess\", description: \"Bad Game\"}")
        justRun { redisInterface.pushToList(listName, game) }
        redisInterface.pushToList(listName, game)

        verify { redisInterface.pushToList(listName, game) }
    }

    @Test
    fun testPushMultipleToList(){
        val redisInterface = mockk<RedisInterface>(relaxed = true)
        val listName: String = "Games"
        val games = listOf<String>("{name: \"Chess\", description:\"Bad\"}", "{name: \"Catan\", description:\"Better\"}")
        justRun { redisInterface.pushToList(listName, games) }
        redisInterface.pushToList(listName, games)

        verify { redisInterface.pushToList(listName, games) }
    }

    @Test
    fun getOneFromList(){
        val redisInterface = mockk<RedisInterface>(relaxed = true)
        val listName: String = "Games"
        val game: String = "{name: \"chess\", description: \"Bad Game\"}"
        val count: Long = 1
        every { redisInterface.getFromList(listName, count) } returns listOf(game)
        val result: List<String> = redisInterface.getFromList(listName, count)

        assertEquals(result, listOf(game))
        verify { redisInterface.getFromList(listName, count) }
    }

    @Test
    fun getMultipleFromList(){
        val redisInterface = mockk<RedisInterface>(relaxed = true)
        val listName: String = "Games"
        val games = arrayOf("{name: \"Chess\", description:\"Bad\"}", "{name: \"Catan\", description:\"Better\"}, \"{name: \"Uno\", description:\"Mild\"}")
        val expectedResult = arrayOf("{name: \"Catan\", description:\"Better\"}, \"{name: \"Uno\", description:\"Mild\"}")
        val count: Long = 2
        every { redisInterface.getFromList(listName, count) } returns listOf(*expectedResult)
        val result: List<String> = redisInterface.getFromList(listName, count)

        assertEquals(result, listOf(*expectedResult))
        verify { redisInterface.getFromList(listName, count) }
    }
}