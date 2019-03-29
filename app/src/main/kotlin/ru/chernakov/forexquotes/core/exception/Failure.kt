package ru.chernakov.forexquotes.core.exception


sealed class Failure : Throwable() {
    object NetworkConnection : Failure()
    object ServerError : Failure()

    abstract class FeatureFailure : Failure()
}
