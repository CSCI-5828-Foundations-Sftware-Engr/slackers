package test.goodboards.app.util

import com.goodboards.app.database.DBHelper
import com.goodboards.db.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import java.sql.Connection

object DBMock {

    val VALUE_DATABASE_URL = "fakeURL"
    val VALUE_DATABASE_USERNAME = "fakeUsername"
    val VALUE_DATABASE_PASSWORD = "fakePassword"

    fun mockDBConnection(): Unit {
        mockkObject(SystemWrapper)
        every { SystemWrapper.getenv("JDBC_DATABASE_URL") } returns VALUE_DATABASE_URL
        every { SystemWrapper.getenv("DATABASE_USERNAME") } returns VALUE_DATABASE_USERNAME
        every { SystemWrapper.getenv("DATABASE_PASSWORD") } returns VALUE_DATABASE_PASSWORD
        val mockedConnection: Connection = mockk(relaxed = true)
        mockkObject(DriverManagerWrapper)
        every {
            DriverManagerWrapper.getConnection(
                "jdbc:fakeURL",
                "fakeUsername",
                "fakePassword"
            )
        } returns mockedConnection
    }

    fun makeGameInfos(count: Int) = ( 1 ..  count).map {
            listOf(it.toString(), it.toString() + "Name", it.toString() + "Description")
        }.toList()

    fun mockGames(count: Int): List<Game> = makeGameInfos(count).map {
        Game(it[0], it[1], it[2])
    }

    fun makeNews(count: Int) = (1 .. count).map {
        listOf(it.toString(), it.toString(), it.toString() + "Title", it.toString() + "Url", it.toString() + "Description")
    }.toList()

    fun mockNews(): List<News> = makeNews(1).map {
        News(it[0], it[1], it[2], it[3], it[4])
    }

    fun makeGame(): Game {
        return Game("id", "name", "desc")
    }

    fun mockDBInterface() : DBInterface {
        mockkObject(DBHelper)
        val dbInterface = mockk<DBInterface>()
        every { DBHelper.getDBInterface() } returns dbInterface
        return dbInterface
    }
}