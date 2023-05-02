package com.goodboards.app.collector.tasks

import com.goodboards.app.collector.RetrieveNewsTask
import com.goodboards.workflow.WorkScheduler

object RetrieveNewsTaskHelper {

    // Delay for 1 hr intervals
    fun scheduleRetrieveNewsTask() {
        val finder = Wrapper.getRetrieveNewsWorkFinder()
        val worker = Wrapper.getRetrieveNewsWorker()
        val scheduler = WorkScheduler<RetrieveNewsTask>(finder, mutableListOf(worker), 60*60)
        scheduler.start()
    }

}