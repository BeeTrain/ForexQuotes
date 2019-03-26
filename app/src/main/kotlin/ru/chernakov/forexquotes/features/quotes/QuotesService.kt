package ru.chernakov.forexquotes.features.quotes

import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuotesService
@Inject constructor(retrofit: Retrofit) : QuotesApi {

    private val quotesApi by lazy { retrofit.create(QuotesApi::class.java) }

    override fun symbols(apiKey: String): Call<List<String>> = quotesApi.symbols(apiKey)

    override fun quotes(symbols: String, apiKey: String): Call<List<Quote>> = quotesApi.quotes(symbols, apiKey)

}