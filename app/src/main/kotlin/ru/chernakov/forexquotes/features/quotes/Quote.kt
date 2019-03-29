package ru.chernakov.forexquotes.features.quotes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Quote(

    @SerializedName("symbol")
    @Expose
    val symbol: String,

    @SerializedName("price")
    @Expose
    val price: Double,

    @SerializedName("bid")
    @Expose
    val bid: Double,

    @SerializedName("ask")
    @Expose
    val ask: Double,

    @SerializedName("timestamp")
    @Expose
    val timestamp: Int
) {
    companion object {
        fun fromQuoteView(quoteView: QuoteView) =
            Quote(quoteView.symbol, quoteView.price, quoteView.bid, quoteView.ask, quoteView.timestamp)
    }
}