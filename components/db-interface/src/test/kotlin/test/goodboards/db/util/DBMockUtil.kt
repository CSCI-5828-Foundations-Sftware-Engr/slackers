package test.goodboards.db.util

import com.goodboards.db.DriverManagerWrapper
import com.goodboards.db.SystemWrapper
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import java.sql.Connection

object DBMockUtil {
    fun mockDBConnection(): Unit {
        mockkObject(SystemWrapper)
        every { SystemWrapper.getenv("JDBC_DATABASE_URL") } returns "fakeURL"
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
}