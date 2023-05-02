package com.goodboards.app.collector.tasks

import com.goodboards.app.collector.RetrieveNewsTask
import com.goodboards.app.collector.RetrieveNewsWorkFinder
import com.goodboards.app.collector.RetrieveNewsWorker
import com.goodboards.workflow.WorkScheduler

object RetrieveNewsTaskHelper {

    // Delay for 30 min intervals
    fun scheduleRetrieveNewsTask() {
        val finder = Wrapper.getRetrieveNewsWorkFinder()
        val worker = Wrapper.getRetrieveNewsWorker()
        val scheduler = WorkScheduler<RetrieveNewsTask>(finder, mutableListOf(worker), 30*60)
        scheduler.start()
    }

}