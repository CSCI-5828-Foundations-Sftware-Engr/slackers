package test.goodboards.app.util

import com.goodboards.app.database.DBHelper
import com.goodboards.db.DBInterface
import com.goodboards.db.DriverManagerWrapper
import com.goodboards.db.Game
import com.goodboards.db.SystemWrapper
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import java.sql.Connection

object DBMock {
    fun mockDBConnection(): Unit {
        mockkObject(SystemWrapper)
        every { SystemWrapper.getenv("DATABASE_URL") } returns "fakeURL"
        every { SystemWrapper.getenv("DATABASE_USERNAME") } returns "fakeUsername"
        every { SystemWrapper.getenv("DATABASE_PASSWORD") } returns "fakePassword"
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

    fun mockDBInterface() : DBInterface {
        mockkObject(DBHelper)
        val dbInterface = mockk<DBInterface>()
        every { DBHelper.getDBInterface() } returns dbInterface
        return dbInterface
    }
}