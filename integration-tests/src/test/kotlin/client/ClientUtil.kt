package client

import io.ktor.client.*
import io.ktor.client.engine.cio.*

object ClientUtil {
    @JvmStatic
    fun getClient() = HttpClient(CIO)
}