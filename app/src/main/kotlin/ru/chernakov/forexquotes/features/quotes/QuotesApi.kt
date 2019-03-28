package ru.chernakov.forexquotes.features.quotes

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal interface QuotesApi {
    companion object {
        private const val SYMBOLS = "symbols"
        private const val QUOTES = "quotes"
        private const val API_KEY = "api_key"
    }

    @GET(SYMBOLS)
    fun symbols(@Query(API_KEY) apiKey: String): Single<List<String>>

    @GET(QUOTES)
    fun quotes(@Query(SYMBOLS) symbols: String, @Query(API_KEY) apiKey: String): Observable<List<Quote>>
}