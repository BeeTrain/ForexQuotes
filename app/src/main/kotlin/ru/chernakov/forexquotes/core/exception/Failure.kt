package ru.chernakov.forexquotes.core.exception


sealed class Failure {

    abstract class FeatureFailure: Failure()
}
