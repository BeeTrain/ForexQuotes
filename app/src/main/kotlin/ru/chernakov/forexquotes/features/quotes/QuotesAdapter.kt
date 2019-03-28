package ru.chernakov.forexquotes.features.quotes

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_qoute.view.*
import ru.chernakov.forexquotes.R
import ru.chernakov.forexquotes.core.extension.formatDate
import ru.chernakov.forexquotes.core.extension.getDateFromUnixTimestamp
import ru.chernakov.forexquotes.core.extension.inflate
import javax.inject.Inject
import kotlin.properties.Delegates

class QuotesAdapter
@Inject constructor() : RecyclerView.Adapter<QuotesAdapter.ViewHolder>() {

    internal var collection: ArrayList<QuoteView> by Delegates.observable(ArrayList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_qoute))

    override fun getItemCount() = collection.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(collection[position])

    fun getVisibleItems(firstVisibleItemPos: Int, lastVisibleItemPos: Int): List<QuoteView> {
        val visibleItems = ArrayList<QuoteView>()
        for (i in firstVisibleItemPos..lastVisibleItemPos) {
            visibleItems.add(collection[i])
        }

        return visibleItems
    }

    fun modifyItem(position: Int, model: QuoteView) {
        collection.set(position, model)
        notifyItemChanged(position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(quoteView: QuoteView) {
            itemView.titleSymbols.text = quoteView.symbol
            itemView.price.text = quoteView.price.toString()
            itemView.bid.text = quoteView.bid.toString()
            itemView.ask.text = quoteView.ask.toString()
            itemView.timestamp.text = formatDate(getDateFromUnixTimestamp(quoteView.timestamp))
        }
    }
}