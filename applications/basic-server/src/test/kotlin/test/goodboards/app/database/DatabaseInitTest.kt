package test.goodboards.app.database

import com.goodboards.app.database.DatabaseInit
import com.goodboards.app.database.EnvHelper
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.mockkStatic
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class DatabaseInitTest {
    companion object {
        private const val ENV_DATABASE_URL = "DATABASE_URL"
        private const val ENV_DATABASE_USERNAME = "DATABASE_USERNAME"
        private const val ENV_DATABASE_PASSWORD = "DATABASE_PASSWORD"

        const val DATABASE_URL = "localhost"
        const val DATABASE_USERNAME = "username"
        const val DATABASE_PASSWORD = "password"

        fun mockEnvironmentCredentials() {
            mockkObject(EnvHelper)
            every { EnvHelper.getEnv(ENV_DATABASE_URL) }  returns DATABASE_URL
            every { EnvHelper.getEnv(ENV_DATABASE_USERNAME) }  returns DATABASE_USERNAME
            every { EnvHelper.getEnv(ENV_DATABASE_PASSWORD) }  returns DATABASE_PASSWORD
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
}