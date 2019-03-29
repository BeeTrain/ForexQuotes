package ru.chernakov.forexquotes.features.quotes

import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuotesService
@Inject constructor(retrofit: Retrofit) : QuotesApi {

    private val quotesApi by lazy { retrofit.create(QuotesApi::class.java) }

    override fun quotes(symbols: String, apiKey: String): Single<List<Quote>> = quotesApi.quotes(symbols, apiKey)

}