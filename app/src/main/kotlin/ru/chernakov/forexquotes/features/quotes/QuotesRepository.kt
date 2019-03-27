package ru.chernakov.forexquotes.features.quotes

import android.content.Context
import io.reactivex.Observable
import io.reactivex.Single
import ru.chernakov.forexquotes.R
import ru.chernakov.forexquotes.core.platform.NetworkHandler
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface QuotesRepository {
    fun symbols(): Single<List<Quote>>

    fun quotes(): Observable<List<Quote>>

    class Network
    @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: QuotesService,
        private val context: Context
    ) : QuotesRepository {

        override fun symbols(): Single<List<Quote>> {
            return when (networkHandler.isConnected) {
                true -> service.symbols(context.getString(R.string.api_key)).map { transform(it) }
                false, null -> Single.error(Throwable())
            }

        }

        override fun quotes(): Observable<List<Quote>> {
            return service.quotes("", "").delay(3000, TimeUnit.MILLISECONDS)
        }
    }
}