package test.goodboards.db

import com.goodboards.db.DBConnection
import com.goodboards.db.DriverManagerWrapper
import com.goodboards.db.SystemWrapper
import org.junit.Test
import kotlin.test.*
import io.mockk.*
import test.goodboards.db.util.DBMockUtil
import java.sql.Connection

class DBConnectionTest {

    @BeforeTest
    fun setup() {
        // Mock System to get env variables
        mockkObject(SystemWrapper)
        every { SystemWrapper.getenv("JDBC_DATABASE_URL") } returns "fakeURL"
        every { SystemWrapper.getenv("DATABASE_USERNAME") } returns "fakeUsername"
        every { SystemWrapper.getenv("DATABASE_PASSWORD") } returns "fakePassword"

        // Mock DriverManager to get connection
        val mockedConnection: Connection = mockk(relaxed = true)
        mockkObject(DriverManagerWrapper)
        every { DriverManagerWrapper.getConnection("jdbc:fakeURL", "fakeUsername", "fakePassword") } returns mockedConnection
    }

    @Test
    fun testHasConnection_No() {
        assertFalse { DBConnection.hasConnection() }
    }

    @Test
    fun testGetCredentials_Exists() {
        val credentials = DBConnection.getCredentials()
        assertEquals("fakeURL", credentials.url)
        assertEquals("fakeUsername", credentials.username)
        assertEquals("fakePassword", credentials.password)
    }

    @Test
    fun testHasConnection_Yes() {
        DBConnection.setConnection("fakeURL", "fakeUsername", "fakePassword")
        assertTrue { DBConnection.hasConnection() }
    }

    @Test
    fun testGetConnection_Exists() {
        DBConnection.setConnection("fakeURL", "fakeUsername", "fakePassword")
        assertNotNull(DBConnection.getConnection())
    }

    @Test
    fun testGetConnection_DoesNotExist() {
        assertFailsWith<Exception>(
            block = {
                DBConnection.getConnection()
            }
        )
    }

    @Test
    fun testSetConnection_Invalid() {
        assertFailsWith<Exception>(
            block = {
                DBConnection.setConnection("badURL", "badUsername", "badPassword")
            }
        )
    }

}