package ru.chernakov.forexquotes.features.quotes

import android.os.Parcel
import ru.chernakov.forexquotes.core.platform.KParcelable
import ru.chernakov.forexquotes.core.platform.parcelableCreator

data class QuoteView(
    val symbol: String,
    val price: Double,
    val bid: Double,
    val ask: Double,
    val timestamp: Int
) : KParcelable {
    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::QuoteView)
    }

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeString(symbol)
            writeDouble(price)
            writeDouble(bid)
            writeDouble(ask)
            writeInt(timestamp)
        }
    }

}