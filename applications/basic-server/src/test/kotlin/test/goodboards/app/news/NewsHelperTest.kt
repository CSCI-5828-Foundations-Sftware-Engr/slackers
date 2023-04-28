package test.goodboards.app.news


import com.goodboards.app.news.NewsHelper
import com.goodboards.app.news.News
import io.mockk.every
import io.mockk.verify
import org.junit.Test
import test.goodboards.app.util.DBMock
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NewsHelperTest {

    @Test
    fun testGetNewsForGame() {
        DBMock.mockDBConnection()
        val dbInterface = DBMock.mockDBInterface()
        val dbNews = DBMock.mockNews()
        every { dbInterface.getNewsForGame("test") } returns dbNews
        val news: List<News> = NewsHelper.getNewsForGame("test");
        verify (exactly = 1) { dbInterface.getNewsForGame("test") }
        assertTrue { dbNews.size == news.size }
    }
}