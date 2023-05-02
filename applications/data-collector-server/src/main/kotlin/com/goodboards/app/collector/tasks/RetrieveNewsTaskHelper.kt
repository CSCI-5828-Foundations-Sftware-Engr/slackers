package com.goodboards.app.collector.tasks

import com.goodboards.app.collector.RetrieveNewsTask
import com.goodboards.workflow.WorkScheduler

object RetrieveNewsTaskHelper {

    // Interval between each api query in seconds.
    // Current interval: 1 hr
    private const val INTERVAL: Long = 3600

    fun scheduleRetrieveNewsTask() {
        val finder = Wrapper.getRetrieveNewsWorkFinder()
        val worker = Wrapper.getRetrieveNewsWorker()
        val scheduler = WorkScheduler<RetrieveNewsTask>(finder, mutableListOf(worker), INTERVAL)
        scheduler.start()
    }

}