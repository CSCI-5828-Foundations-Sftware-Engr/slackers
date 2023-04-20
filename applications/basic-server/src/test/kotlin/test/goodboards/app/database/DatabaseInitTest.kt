package test.goodboards.app.database

import com.goodboards.app.database.ConnectionHelper
import com.goodboards.app.database.DatabaseInit
import com.goodboards.app.database.EnvHelper
import com.goodboards.app.database.UUIDHelper
import io.mockk.*
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.Statement
import java.util.*
import kotlin.test.BeforeTest
import kotlin.test.assertEquals



class DatabaseInitTest {
    companion object {
        private const val ENV_DATABASE_URL = "DATABASE_URL"
        private const val ENV_DATABASE_USERNAME = "DATABASE_USERNAME"
        private const val ENV_DATABASE_PASSWORD = "DATABASE_PASSWORD"

        const val DATABASE_URL = "postgresql:localhost"

        const val DATABASE_USERNAME = "username"
        const val DATABASE_PASSWORD = "password"

        fun mockEnvironmentCredentials() {
            mockkObject(EnvHelper)
            every { EnvHelper.getEnv(ENV_DATABASE_URL) }  returns DATABASE_URL
            every { EnvHelper.getEnv(ENV_DATABASE_USERNAME) }  returns DATABASE_USERNAME
            every { EnvHelper.getEnv(ENV_DATABASE_PASSWORD) }  returns DATABASE_PASSWORD
        }

        fun mockUUID(){
            mockkObject(UUIDHelper)
            val uid = UUID.fromString("54228581-9c22-48ce-b775-41c06dd0f221")
            every { UUIDHelper.randomUUID() } returns uid
        }


        fun mockDBConnection(): PreparedStatement{
            val connection = mockk<Connection>()
            mockkObject(ConnectionHelper)
            every{ ConnectionHelper.getConnection() } returns connection
            val statement = mockk<Statement>()
            every{ connection.createStatement() } returns statement
            val preparedStatement = mockk<PreparedStatement>()
            every { connection.prepareStatement("INSERT INTO goodboards.games(id, name, description) VALUES ('54228581-9c22-48ce-b775-41c06dd0f221', '\"UnoTest\"', '\"Friendship destroyer.\"');") } returns preparedStatement
            every { connection.prepareStatement("INSERT INTO goodboards.games(id, name, description) VALUES ('54228581-9c22-48ce-b775-41c06dd0f221', '\"ChessTest\"', '\"Mind bender.\"');") } returns preparedStatement
            every { connection.prepareStatement("INSERT INTO goodboards.games(id, name, description) VALUES ('54228581-9c22-48ce-b775-41c06dd0f221', '\"PokerTest\"', '\"Trickster raiser.\"');") } returns preparedStatement
            every { preparedStatement.executeUpdate() } returns 0
            every { preparedStatement.close() } returns Unit
            every { connection.close() } returns Unit
            every { statement.close() } returns Unit
            return preparedStatement
        }

    }
    @Test
    fun testGetsCredential() {
        mockEnvironmentCredentials()
        val actualCredential = DatabaseInit.getDatabaseCredentials()
        assertEquals(DATABASE_URL, actualCredential.url)
        assertEquals(DATABASE_USERNAME, actualCredential.username)
        assertEquals(DATABASE_PASSWORD, actualCredential.password)
    }

    @Test
    fun testReadGameJsonIntoDBSuccess(){
        mockEnvironmentCredentials()
        mockUUID()
        val preparedStatement = mockDBConnection()
        DatabaseInit.readGameJsonIntoDB("game_info_test.json")
        verify(exactly = 3) { preparedStatement.executeUpdate() }
    }

}