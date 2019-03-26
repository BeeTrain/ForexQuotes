package ru.chernakov.forexquotes.core.interactor

import io.reactivex.observers.DisposableObserver

internal open class BaseObserver<T> : DisposableObserver<T>() {

    override fun onNext(t: T) {}

    override fun onComplete() {}

    override fun onError(e: Throwable) {}
}