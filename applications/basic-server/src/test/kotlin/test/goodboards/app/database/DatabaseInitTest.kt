package test.goodboards.app.database

import com.goodboards.app.database.DBHelper
import com.goodboards.app.database.DatabaseInit
import com.goodboards.app.database.UUIDHelper
import io.mockk.*
import org.junit.Test
import test.goodboards.app.util.DBMock
import test.goodboards.app.util.MockUtil
import java.sql.PreparedStatement
import java.sql.Statement
import java.util.*
import kotlin.test.assertEquals



class DatabaseInitTest {

    companion object {
        fun mockUUID(){
            mockkObject(UUIDHelper)
            val uid = UUID.fromString("54228581-9c22-48ce-b775-41c06dd0f221")
            every { UUIDHelper.randomUUID() } returns uid
        }


        fun mockGamesInit(): PreparedStatement{
            val connection =  MockUtil.mockDBConnection()
            val statement = mockk<Statement>()
            every{ connection.createStatement() } returns statement
            val preparedStatement = mockk<PreparedStatement>()
            every { connection.prepareStatement("INSERT INTO goodboards.games(id, name, description) VALUES ('54228581-9c22-48ce-b775-41c06dd0f221', 'UnoTest', 'Friendship destroyer.');") } returns preparedStatement
            every { connection.prepareStatement("INSERT INTO goodboards.games(id, name, description) VALUES ('54228581-9c22-48ce-b775-41c06dd0f221', 'ChessTest', 'Mind bender.');") } returns preparedStatement
            every { connection.prepareStatement("INSERT INTO goodboards.games(id, name, description) VALUES ('54228581-9c22-48ce-b775-41c06dd0f221', 'PokerTest', 'Trickster raiser.');") } returns preparedStatement
            every { preparedStatement.executeUpdate() } returns 0
            every { preparedStatement.close() } returns Unit
            every { connection.close() } returns Unit
            every { statement.close() } returns Unit
            return preparedStatement
        }

    }
    @Test
    fun testGetsCredential() {
        DBMock.mockDBConnection()
        val actualCredential = DBHelper.getDatabaseCredentials()
        assertEquals(DBMock.VALUE_DATABASE_URL, actualCredential.url)
        assertEquals(DBMock.VALUE_DATABASE_USERNAME, actualCredential.username)
        assertEquals(DBMock.VALUE_DATABASE_PASSWORD, actualCredential.password)
    }

    @Test
    fun testReadGameJsonIntoDBSuccess(){
        MockUtil.mockEnvironmentCredentials()
        mockUUID()
        val preparedStatement = mockGamesInit()
        DatabaseInit.readGameJsonIntoDB("game_info_test.json")
        verify(exactly = 3) { preparedStatement.executeUpdate() }
    }

}