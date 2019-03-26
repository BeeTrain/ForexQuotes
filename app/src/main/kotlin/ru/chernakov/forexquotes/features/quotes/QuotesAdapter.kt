package ru.chernakov.forexquotes.features.quotes

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.chernakov.forexquotes.R
import ru.chernakov.forexquotes.core.extension.inflate
import javax.inject.Inject
import kotlin.properties.Delegates

class QuotesAdapter
@Inject constructor() : RecyclerView.Adapter<QuotesAdapter.ViewHolder>() {

    internal var collection: List<QuoteView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_qoute))

    override fun getItemCount() = collection.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(collection[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(quoteView: QuoteView) {
            itemView
        }
    }
}