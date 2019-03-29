package ru.chernakov.forexquotes.features.quotes

import androidx.lifecycle.MutableLiveData
import ru.chernakov.forexquotes.core.interactor.BaseObserverSingle
import ru.chernakov.forexquotes.core.platform.BaseViewModel
import java.util.*
import javax.inject.Inject

class QuotesViewModel
@Inject constructor(
    private val getQuotes: GetQuotes,
    private val quotesAdapter: QuotesAdapter
) : BaseViewModel() {

    var quotes: MutableLiveData<List<QuoteView>> = MutableLiveData()

    private fun handleQuotesList(quotes: List<Quote>) {
        this.quotes.value = quotes.map { QuoteView(it.symbol, it.price, it.bid, it.ask, it.timestamp) }
    }

    fun initPeriodicUpdates() {
        val timer = Timer()
        val updateVisibleTask = UpdateVisibleTask()
        timer.scheduleAtFixedRate(updateVisibleTask, 0, 5000)
    }

    private inner class UpdateVisibleTask : TimerTask() {
        override fun run() {
            var items = ""
            if (quotesAdapter.visibleItems.isNotEmpty()) {
                items = quotesAdapter.visibleItems.joinToString { it.symbol }.replace(", ", ",")
            }
            getQuotes.execute(QuotesObserver(), items)
        }
    }

    private inner class QuotesObserver : BaseObserverSingle<List<Quote>>() {
        override fun onSuccess(t: List<Quote>) {
            val quotesUpdate = ArrayList<Quote>()

            if (quotes.value == null) {
                handleQuotesList(t)
            } else {
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
    }
}