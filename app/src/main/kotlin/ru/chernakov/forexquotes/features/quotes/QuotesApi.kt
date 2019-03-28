package ru.chernakov.forexquotes.features.quotes

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal interface QuotesApi {
    companion object {
        private const val API_KEY = "api_key"
        private const val QUOTES = "quotes"
        private const val PAIRS = "pairs"
    }

    @GET(QUOTES)
    fun allQuotes(@Query(API_KEY) apiKey: String): Single<List<Quote>>

    @GET(QUOTES)
    fun quotes(@Query(PAIRS, encoded = true) symbols: String, @Query(API_KEY) apiKey: String): Observable<List<Quote>>
}