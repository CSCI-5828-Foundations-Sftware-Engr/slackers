package test.goodboards.app.game

import com.goodboards.app.game.Game
import com.goodboards.app.game.GamesHelper
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.verify
import org.junit.After
import org.junit.Test
import test.goodboards.app.util.DBMock
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GamesHelperTest {

    @Test
    fun testAllGames() {
        DBMock.mockDBConnection()
        val dbInterface = DBMock.mockDBInterface()
        val dbGames = DBMock.mockGames(3)
        every { dbInterface.getAllGames() } returns dbGames
        val games: List<Game> = GamesHelper.getAllGames();
        verify (exactly = 1) { dbInterface.getAllGames() }
        assertTrue { dbGames.size == games.size }
        (dbGames zip games ).map {
            assertEquals(it.first.uuid, it.second.id)
            assertEquals(it.first.name, it.second.name)
            assertEquals(it.first.description, it.second.description)
        }
    }

    @After
    fun clearSetup() {
        clearAllMocks()
    }
}