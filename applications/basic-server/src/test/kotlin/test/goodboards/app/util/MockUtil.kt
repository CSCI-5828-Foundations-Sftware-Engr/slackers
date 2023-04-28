package test.goodboards.app.util

import com.goodboards.app.database.ConnectionHelper
import com.goodboards.app.util.EnvHelper
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import test.goodboards.app.database.DatabaseInitTest
import java.sql.Connection

object MockUtil {
    private const val ENV_DATABASE_URL = "JDBC_DATABASE_URL"
    private const val ENV_DATABASE_USERNAME = "DATABASE_USERNAME"
    private const val ENV_DATABASE_PASSWORD = "DATABASE_PASSWORD"


    fun mockEnvironmentCredentials() {
        mockkObject(EnvHelper)
        every { EnvHelper.getEnv(ENV_DATABASE_URL) }  returns DBMock.DATABASE_URL
        every { EnvHelper.getEnv(ENV_DATABASE_USERNAME) }  returns DBMock.DATABASE_USERNAME
        every { EnvHelper.getEnv(ENV_DATABASE_PASSWORD) }  returns DBMock.DATABASE_PASSWORD
    }

    fun mockDBConnection(): Connection {
        val connection = mockk<Connection>()
        mockkObject(ConnectionHelper)
        every{ ConnectionHelper.getConnection() } returns connection
        return connection
    }
}