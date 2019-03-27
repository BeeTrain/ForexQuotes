package ru.chernakov.forexquotes.features.quotes

import java.util.*


fun transform(symbols: List<String>): List<Quote> {
    val transformed = ArrayList<Quote>()
    for (String in symbols) {
        transformed.add(Quote.symbolOnly(String))
    }

    return transformed
}

fun toQuote(quoteView: QuoteView): Quote {
    return Quote(quoteView.symbol, quoteView.price, quoteView.bid, quoteView.ask, quoteView.timestamp)
}

fun transformFromView(quoteViews: List<QuoteView>): List<Quote> {
    val transformed = ArrayList<Quote>()
    for (QuoteView in quoteViews) {
        transformed.add(Quote(QuoteView.symbol, QuoteView.price, QuoteView.bid, QuoteView.ask, QuoteView.timestamp))
    }

    return transformed
}