package com.goodboards.app.collector

import com.goodboards.workflow.Worker
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory

class RetrieveNewsWorker(override val name: String = "data-collector") : Worker<RetrieveNewsTask> {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun execute(task: RetrieveNewsTask) {
        runBlocking {
            logger.info("starting data collection.")

            // todo - data collection happens here

            logger.info("completed data collection.")
        }
    }
}