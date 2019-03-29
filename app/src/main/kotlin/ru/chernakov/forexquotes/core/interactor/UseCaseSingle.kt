package ru.chernakov.forexquotes.core.interactor

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import ru.chernakov.forexquotes.core.executor.PostExecutionThread
import ru.chernakov.forexquotes.core.executor.ThreadExecutor

abstract class UseCaseSingle<T, Params>(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    internal abstract fun buildUseCaseSingle(params: Params): Single<T>

    fun execute(observer: DisposableSingleObserver<T>, params: Params) {

        val single = this.buildUseCaseSingle(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
        addDisposable(single.subscribeWith(observer))
    }

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}