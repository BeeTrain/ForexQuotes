package ru.chernakov.forexquotes.core.interactor

import io.reactivex.observers.DisposableSingleObserver

internal open class BaseObserverSingle<T> : DisposableSingleObserver<T>() {

    override fun onSuccess(t: T) {}

    override fun onError(e: Throwable) {}
}
