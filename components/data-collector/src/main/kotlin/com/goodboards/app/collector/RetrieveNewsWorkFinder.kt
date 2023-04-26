package com.goodboards.app.collector

import com.goodboards.workflow.WorkFinder
import org.slf4j.LoggerFactory

class RetrieveNewsWorkFinder : WorkFinder<RetrieveNewsTask> {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun findRequested(name: String): List<RetrieveNewsTask> {
        logger.info("finding work.")

        val work = RetrieveNewsTask("some info")

        return mutableListOf(work)
    }

    override fun markCompleted(info: RetrieveNewsTask) {
        logger.info("marking work complete.")
    }
}