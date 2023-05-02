package test.goodboards.app.collector

import com.goodboards.app.collector.RetrieveNewsTask
import com.goodboards.app.collector.RetrieveNewsWorker
import com.goodboards.app.collector.RetrieveNewsWorkFinder
import com.goodboards.app.collector.tasks.RetrieveNewsTaskHelper
import com.goodboards.app.collector.tasks.Wrapper
import com.goodboards.workflow.WorkScheduler
import io.mockk.*
import org.junit.Test

class RetrieveNewsTaskHelperTest {

    @Test
    fun testScheduleRetrieveNewsTask() {
        mockkObject(Wrapper)
        val finder = mockk<RetrieveNewsWorkFinder>()
        val worker = mockk<RetrieveNewsWorker>()
        every { Wrapper.getRetrieveNewsWorkFinder() } returns finder
        every { Wrapper.getRetrieveNewsWorker() } returns worker

        mockkConstructor(WorkScheduler::class)
        every { anyConstructed<WorkScheduler<RetrieveNewsTask>>().start() } returns Unit

        RetrieveNewsTaskHelper.scheduleRetrieveNewsTask()
    }

}