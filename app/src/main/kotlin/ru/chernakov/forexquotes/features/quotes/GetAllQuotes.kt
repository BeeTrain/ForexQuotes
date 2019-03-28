package ru.chernakov.forexquotes.features.quotes

import io.reactivex.Single
import ru.chernakov.forexquotes.core.executor.PostExecutionThread
import ru.chernakov.forexquotes.core.executor.ThreadExecutor
import ru.chernakov.forexquotes.core.interactor.UseCaseSingle
import javax.inject.Inject

class GetAllQuotes
@Inject constructor(
    private val quotesRepository: QuotesRepository, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread
) : UseCaseSingle<List<Quote>, Void>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseSingle(params: Void?): Single<List<Quote>> {
        return quotesRepository.allQuotes()
    }
}
