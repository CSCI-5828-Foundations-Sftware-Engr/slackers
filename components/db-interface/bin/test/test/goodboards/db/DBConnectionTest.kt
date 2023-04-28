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
        DBMockUtil.mockDBConnection()
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