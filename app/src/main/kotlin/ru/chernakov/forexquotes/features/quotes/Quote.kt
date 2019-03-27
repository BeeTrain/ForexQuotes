package ru.chernakov.forexquotes.features.quotes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.chernakov.forexquotes.core.extension.empty

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
        fun empty() = Quote(String.empty(), 0.0, 0.0, 0.0, 0)
        fun symbolOnly(symbol: String) = Quote(symbol, 0.0, 0.0, 0.0, 0)
    }
}