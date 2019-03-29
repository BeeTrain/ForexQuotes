package ru.chernakov.forexquotes.features.quotes

import io.reactivex.Single
import ru.chernakov.forexquotes.core.executor.PostExecutionThread
import ru.chernakov.forexquotes.core.executor.ThreadExecutor
import ru.chernakov.forexquotes.core.interactor.UseCaseSingle
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetQuotes
@Inject constructor(
    private val quotesRepository: QuotesRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCaseSingle<List<Quote>, String>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseSingle(params: String): Single<List<Quote>> {
        return quotesRepository.quotes(params)
    }
}
