package ru.chernakov.forexquotes.features.quotes

import android.content.Context
import io.reactivex.Single
import ru.chernakov.forexquotes.R
import ru.chernakov.forexquotes.core.exception.Failure
import ru.chernakov.forexquotes.core.platform.NetworkHandler
import javax.inject.Inject

interface QuotesRepository {

    fun quotes(items: String): Single<List<Quote>>

    class Network
    @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: QuotesService,
        private val context: Context
    ) : QuotesRepository {

        override fun quotes(items: String): Single<List<Quote>> {
            return when (networkHandler.isConnected) {
                true -> service.quotes(items, context.getString(R.string.api_key))
                false, null -> Single.error(Failure.NetworkConnection)
            }
        }
    }
}