package ru.chernakov.forexquotes.features.quotes

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.chernakov.forexquotes.core.interactor.BaseObserver
import ru.chernakov.forexquotes.core.interactor.BaseObserverSingle
import ru.chernakov.forexquotes.core.platform.BaseViewModel
import javax.inject.Inject

class QuotesViewModel
@Inject constructor(
    private val getQuotes: GetQuotes,
    private val getAllQuotes: GetAllQuotes,
    private val quotesAdapter: QuotesAdapter
) : BaseViewModel() {

    var quotes: MutableLiveData<List<QuoteView>> = MutableLiveData()

    var visibleItems = ArrayList<QuoteView>()

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                val lm = recyclerView.layoutManager as LinearLayoutManager
                val adapter = recyclerView.adapter as QuotesAdapter

                val first = lm.findFirstCompletelyVisibleItemPosition()
                val last = lm.findLastCompletelyVisibleItemPosition()
                visibleItems = adapter.getVisibleItems(first, last) as ArrayList<QuoteView>

                val pairs = visibleItems.joinToString { it.symbol }.replace(", ", ",")
                loadQuotes(pairs)
            }

        }
    }

    fun loadAllQuotes() {
        getAllQuotes.execute(QuotesObserverSingle(), null)
    }

    fun loadQuotes(pairs: String) {
        getQuotes.execute(QuotesObserver(), pairs)
    }

    private fun handleQuotesList(quotes: List<Quote>) {
        this.quotes.value = quotes.map { QuoteView(it.symbol, it.price, it.bid, it.ask, it.timestamp) }
    }

    private inner class QuotesObserver : BaseObserver<List<Quote>>() {
        override fun onNext(t: List<Quote>) {
            var quotesUpdate = ArrayList<Quote>()

            for (QuoteView in quotes.value!!) {
                var isUpdated = false
                for (Quote in t) {
                    if (Quote.symbol == QuoteView.symbol) {
                        quotesUpdate.add(Quote)
                        isUpdated = true
                    }
                }
                if (!isUpdated) {
                    quotesUpdate.add(
                        Quote(
                            QuoteView.symbol,
                            QuoteView.price,
                            QuoteView.bid,
                            QuoteView.ask,
                            QuoteView.timestamp
                        )
                    )
                }
            }
            handleQuotesList(quotesUpdate)
        }
    }

    private inner class QuotesObserverSingle : BaseObserverSingle<List<Quote>>() {

        override fun onSuccess(t: List<Quote>) {
            handleQuotesList(t)
        }
    }
}