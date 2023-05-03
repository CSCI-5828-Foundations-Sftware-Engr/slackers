package test.goodboards.app.collector

import com.goodboards.app.collector.RetrieveNewsWorkFinder
import org.junit.Test

class RetrieveNewsWorkFinderTest {

    @Test
    fun testFindRequestedSuccess() {
        // Test
        val finder = RetrieveNewsWorkFinder()
        val items = finder.findRequested("Happy Path Test")

        // Expect: RetrieveNewsTask with "some info"
        assert(items.size == 1)
        assert(items[0].info == "some info")
    }

}