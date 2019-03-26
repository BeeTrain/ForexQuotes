package ru.chernakov.forexquotes.features.quotes

import android.database.Observable
import ru.chernakov.forexquotes.core.platform.NetworkHandler
import javax.inject.Inject

interface QuotesRepository {
    fun symbols(): List<List<String>>

    fun quotes(): Observable<List<Quote>>

    class Network
    @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: QuotesService
    ) : QuotesRepository {

        override fun symbols(): List<List<String>> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun quotes(): Observable<List<Quote>> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}