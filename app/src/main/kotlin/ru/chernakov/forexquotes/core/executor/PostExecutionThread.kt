package ru.chernakov.forexquotes.core.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}