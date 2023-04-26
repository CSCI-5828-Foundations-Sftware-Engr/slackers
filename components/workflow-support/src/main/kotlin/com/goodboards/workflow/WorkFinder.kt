package com.goodboards.workflow

interface WorkFinder<T> {
    fun findRequested(name: String): List<T>

    fun markCompleted(info: T)
}