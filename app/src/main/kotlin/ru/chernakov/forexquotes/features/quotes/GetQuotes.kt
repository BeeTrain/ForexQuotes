package ru.chernakov.forexquotes.features.quotes

import io.reactivex.Observable
import ru.chernakov.forexquotes.core.executor.PostExecutionThread
import ru.chernakov.forexquotes.core.executor.ThreadExecutor
import ru.chernakov.forexquotes.core.interactor.UseCaseObservable
import javax.inject.Inject

class GetQuotes
@Inject constructor(
    private val quotesRepository: QuotesRepository, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread
) : UseCaseObservable<List<Quote>, Void>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Void?): Observable<List<Quote>> {
        return quotesRepository.quotes()
    }
}
