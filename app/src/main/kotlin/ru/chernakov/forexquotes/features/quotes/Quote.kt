package ru.chernakov.forexquotes.features.quotes

import ru.chernakov.forexquotes.core.extension.empty

data class Quote(
    val symbol: String,
    val price: Double,
    val bid: Double,
    val ask: Double,
    val timestamp: Int
) {
    companion object {
        fun empty() = Quote(String.empty(), 0.0, 0.0, 0.0, 0)
    }
}