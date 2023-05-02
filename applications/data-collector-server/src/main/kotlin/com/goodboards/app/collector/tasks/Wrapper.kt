package com.goodboards.app.collector.tasks

import com.goodboards.app.collector.RetrieveNewsWorkFinder
import com.goodboards.app.collector.RetrieveNewsWorker

object Wrapper {

    fun getRetrieveNewsWorkFinder(): RetrieveNewsWorkFinder {
        return RetrieveNewsWorkFinder()
    }

    fun getRetrieveNewsWorker(): RetrieveNewsWorker {
        return RetrieveNewsWorker()
    }

}