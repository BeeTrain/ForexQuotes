package ru.chernakov.forexquotes.features.quotes

import java.util.*


fun transform(symbols: List<String>): List<Quote> {
    val transformed = ArrayList<Quote>()
    for (String in symbols) {
        transformed.add(Quote.symbolOnly(String))
    }

    return transformed
}