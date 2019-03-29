package ru.chernakov.forexquotes.features.quotes

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_qoute.view.*
import ru.chernakov.forexquotes.R
import ru.chernakov.forexquotes.core.extension.formatDate
import ru.chernakov.forexquotes.core.extension.getDateFromUnixTimestamp
import ru.chernakov.forexquotes.core.extension.inflate
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.properties.Delegates

@Singleton
class QuotesAdapter
@Inject constructor(val context: Context) : RecyclerView.Adapter<QuotesAdapter.ViewHolder>() {

    var layoutManager = LinearLayoutManager(context)

    internal var visibleItems: List<QuoteView> = ArrayList()

    internal var collection: ArrayList<QuoteView> by Delegates.observable(ArrayList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        updateVisibleItems()
        return ViewHolder(context, parent.inflate(R.layout.item_qoute))
    }

    override fun getItemCount() = collection.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(collection[position])

    private fun updateVisibleItems() {
        val firstPosition = layoutManager.findFirstVisibleItemPosition()
        val lastPosition = layoutManager.findLastVisibleItemPosition()
        if (firstPosition > -1 && lastPosition > -1) {
            val visibleItems = ArrayList<QuoteView>()
            for (i in firstPosition..lastPosition) {
                visibleItems.add(collection[i])
            }

            this.visibleItems = visibleItems
        }
    }

    class ViewHolder(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(quoteView: QuoteView) {
            itemView.titleSymbols.text = quoteView.symbol
            itemView.price.text = quoteView.price.toString()
            itemView.bid.text = context.getString(R.string.bid_label, quoteView.bid.toString())
            itemView.ask.text = context.getString(R.string.ask_label, quoteView.ask.toString())
            itemView.timestamp.text = formatDate(getDateFromUnixTimestamp(quoteView.timestamp))
        }
    }
}