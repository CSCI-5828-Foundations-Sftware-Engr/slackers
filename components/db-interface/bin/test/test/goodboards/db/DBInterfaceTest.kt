package test.goodboards.db

import com.goodboards.db.Game
import com.goodboards.db.DBInterface
import com.goodboards.db.DBConnection
import org.junit.Test
import kotlin.test.*
import io.mockk.*
import java.sql.Connection

class DBInterfaceTest {

    @Test
    fun testIsConnected_ConnectionExists() {
        // given -> setup
        val mockedConnection: Connection = mockk(relaxed = true)
        every { mockedConnection.isValid(0) } returns true
        val mockedDBConnection: DBConnection = mockk(relaxed = true)
        every { mockedDBConnection.getConnection() } returns mockedConnection

        // when -> test action
        val dbInterface = DBInterface(mockedDBConnection)
        val status = dbInterface.isConnected()

        // then -> verify results
        assertTrue { status }
    }

    @Test
    fun testIsConnected_NoConnection() {
        // given -> setup
        val mockedConnection: Connection = mockk(relaxed = true)
        every { mockedConnection.isValid(0) } returns false
        val mockedDBConnection: DBConnection = mockk(relaxed = true)
        every { mockedDBConnection.getConnection() } returns mockedConnection

        // when -> test action
        val dbInterface = DBInterface(mockedDBConnection)
        val status = dbInterface.isConnected()

        // then -> verify results
        assertFalse { status }
    }

    @Test
    fun testGetAllGames_Multiple() {
        // given -> setup
        // *** Setup desired game values
        val games = mutableListOf(
            Game("1", "Chess", "Classic Chess"),
            Game("2", "Uno", "Classic Uno")
        )
        // *** Setup query
        val helper = DBInterfaceTestHelpers()
        helper.parseGames(games)
        val mockedResultSet = helper.getMockedResultSet()
        val mockedStatement = helper.getPreparedStatement(mockedResultSet)
        // *** Mock DB Connection
        val mockedConnection: Connection = mockk(relaxed = true)
        every { mockedConnection.prepareStatement("SELECT * FROM goodboards.games;") } returns mockedStatement
        val mockedDBConnection: DBConnection = mockk(relaxed = true)
        every { mockedDBConnection.getConnection() } returns mockedConnection

        // when -> test action
        val dbInterface = DBInterface(mockedDBConnection)
        val result = dbInterface.getAllGames()

        // then -> verify results
        assertEquals(games, result)
    }

    @Test
    fun testGetAllGames_One() {
        // given -> setup
        // *** Setup desired game values
        val games = mutableListOf(
            Game("1", "Chess", "Classic Chess")
        )
        // *** Setup query
        val helper = DBInterfaceTestHelpers()
        helper.parseGames(games)
        val mockedResultSet = helper.getMockedResultSet()
        val mockedStatement = helper.getPreparedStatement(mockedResultSet)
        // *** Mock DB Connection
        val mockedConnection: Connection = mockk(relaxed = true)
        every { mockedConnection.prepareStatement("SELECT * FROM goodboards.games;") } returns mockedStatement
        val mockedDBConnection: DBConnection = mockk(relaxed = true)
        every { mockedDBConnection.getConnection() } returns mockedConnection

        // when -> test action
        val dbInterface = DBInterface(mockedDBConnection)
        val result = dbInterface.getAllGames()

        // then -> verify results
        assertEquals(games, result)
    }

    @Test
    fun testGetAllGames_Zero() {
        // given -> setup
        // *** Setup desired game values
        val games = listOf<Game>()
        // *** Setup query
        val helper = DBInterfaceTestHelpers()
        helper.parseGames(games)
        val mockedResultSet = helper.getMockedResultSet()
        val mockedStatement = helper.getPreparedStatement(mockedResultSet)
        // *** Mock DB Connection
        val mockedConnection: Connection = mockk(relaxed = true)
        every { mockedConnection.prepareStatement("SELECT * FROM goodboards.games;") } returns mockedStatement
        val mockedDBConnection: DBConnection = mockk(relaxed = true)
        every { mockedDBConnection.getConnection() } returns mockedConnection

        // when -> test action
        val dbInterface = DBInterface(mockedDBConnection)
        val result = dbInterface.getAllGames()

        // then -> verify results
        assertEquals(games, result)
    }

    @Test
    fun testGetGameById_Exists() {
        // given -> setup
        // *** Setup desired game values
        val games = mutableListOf(
            Game("1", "Chess", "Classic Chess")
        )
        // *** Setup query
        val helper = DBInterfaceTestHelpers()
        helper.parseGames(games)
        val mockedResultSet = helper.getMockedResultSet()
        val mockedStatement = helper.getPreparedStatement(mockedResultSet)
        // *** Mock DB Connection
        val mockedConnection: Connection = mockk(relaxed = true)
        every { mockedConnection.prepareStatement("SELECT * FROM goodboards.games WHERE id='1';") } returns mockedStatement
        val mockedDBConnection: DBConnection = mockk(relaxed = true)
        every { mockedDBConnection.getConnection() } returns mockedConnection

        // when -> test action
        val dbInterface = DBInterface(mockedDBConnection)
        val result = dbInterface.getGameById("1")

        // then -> verify results
        assertEquals(games[0], result)
    }

    @Test
    fun testGetGameById_DoesNotExist() {
        // given -> setup
        // *** Setup desired game values
        val games = listOf<Game>()
        // *** Setup query
        val helper = DBInterfaceTestHelpers()
        helper.parseGames(games)
        val mockedResultSet = helper.getMockedResultSet()
        val mockedStatement = helper.getPreparedStatement(mockedResultSet)
        // *** Mock DB Connection
        val mockedConnection: Connection = mockk(relaxed = true)
        every { mockedConnection.prepareStatement("SELECT * FROM goodboards.games WHERE id='1';") } returns mockedStatement
        val mockedDBConnection: DBConnection = mockk(relaxed = true)
        every { mockedDBConnection.getConnection() } returns mockedConnection

        // when -> test action
        val dbInterface = DBInterface(mockedDBConnection)

        // then -> verify results
        assertFailsWith<Exception>(
            block = {
                dbInterface.getGameById("1")
            }
        )
    }

    @Test
    fun testGetGameByName_Exists() {
        // given -> setup
        // *** Setup desired game values
        val games = mutableListOf(
            Game("1", "Chess", "Classic Chess")
        )
        // *** Setup query
        val helper = DBInterfaceTestHelpers()
        helper.parseGames(games)
        val mockedResultSet = helper.getMockedResultSet()
        val mockedStatement = helper.getPreparedStatement(mockedResultSet)
        // *** Mock DB Connection
        val mockedConnection: Connection = mockk(relaxed = true)
        every { mockedConnection.prepareStatement("SELECT * FROM goodboards.games WHERE name='Chess';") } returns mockedStatement
        val mockedDBConnection: DBConnection = mockk(relaxed = true)
        every { mockedDBConnection.getConnection() } returns mockedConnection

        // when -> test action
        val dbInterface = DBInterface(mockedDBConnection)
        val result = dbInterface.getGameByName("Chess")

        // then -> verify results
        assertEquals(games[0], result)
    }

    @Test
    fun testGetGameByName_DoesNotExist() {
        // given -> setup
        // *** Setup desired game values
        val games = listOf<Game>()
        // *** Setup query
        val helper = DBInterfaceTestHelpers()
        helper.parseGames(games)
        val mockedResultSet = helper.getMockedResultSet()
        val mockedStatement = helper.getPreparedStatement(mockedResultSet)
        // *** Mock DB Connection
        val mockedConnection: Connection = mockk(relaxed = true)
        every { mockedConnection.prepareStatement("SELECT * FROM goodboards.games WHERE name='Chess';") } returns mockedStatement
        val mockedDBConnection: DBConnection = mockk(relaxed = true)
        every { mockedDBConnection.getConnection() } returns mockedConnection

        // when -> test action
        val dbInterface = DBInterface(mockedDBConnection)

        // then -> verify results
        assertFailsWith<Exception>(
            block = {
                dbInterface.getGameByName("Chess")
            }
        )
    }
    //TODO: Write testcases for deleteGameByID and addGame.


    /*  Things to Test
    * - addGame() -- add 1 game, add 2 games, try to add a game already added?
    * - deleteGameById() -- when successful delete, when incorrect delete
    * */

}