package ru.chernakov.forexquotes.features.quotes

import android.content.Context
import io.reactivex.Observable
import io.reactivex.Single
import ru.chernakov.forexquotes.R
import ru.chernakov.forexquotes.core.platform.NetworkHandler
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface QuotesRepository {
    fun allQuotes(): Single<List<Quote>>

    fun quotes(items: String): Observable<List<Quote>>

    class Network
    @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: QuotesService,
        private val context: Context
    ) : QuotesRepository {

        override fun allQuotes(): Single<List<Quote>> {
            return when (networkHandler.isConnected) {
                true -> service.allQuotes(context.getString(R.string.api_key))
                false, null -> Single.error(Throwable())
            }
        }

        override fun quotes(items: String): Observable<List<Quote>> {
            return service.quotes(items, context.getString(R.string.api_key))
                .repeatWhen { it.delay(5000, TimeUnit.MILLISECONDS) }
        }
    }
}