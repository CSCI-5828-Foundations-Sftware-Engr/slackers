package test.goodboards.app

import com.goodboards.app.module
import io.ktor.server.testing.*
import io.mockk.clearAllMocks
import org.junit.After
import org.junit.Before

open class BaseAppTest {
    protected fun testApp(callback: TestApplicationEngine.() -> Unit) {
        withTestApplication({ module() }) { callback() }
    }

    @After
    fun clearSetup() {
        clearAllMocks()
    }
}