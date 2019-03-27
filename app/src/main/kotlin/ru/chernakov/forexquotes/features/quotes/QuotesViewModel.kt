package ru.chernakov.forexquotes.features.quotes

import androidx.lifecycle.MutableLiveData
import ru.chernakov.forexquotes.core.interactor.BaseObserverSingle
import ru.chernakov.forexquotes.core.platform.BaseViewModel
import javax.inject.Inject

class QuotesViewModel
@Inject constructor(private val getQuotes: GetQuotes, private val getSymbols: GetSymbols) : BaseViewModel() {

    var quotes: MutableLiveData<List<QuoteView>> = MutableLiveData()

    fun loadQuotes() {
        getSymbols.execute(QuotesObserver(), null)
    }

    private fun handleQuotesList(quotes: List<Quote>) {
        this.quotes.value = quotes.map { QuoteView(it.symbol, it.price, it.bid, it.ask, it.timestamp) }
    }

    private inner class QuotesObserver : BaseObserverSingle<List<Quote>>() {

        override fun onSuccess(t: List<Quote>) {
            handleQuotesList(t)
        }

        override fun onError(e: Throwable) {
            super.onError(e)
        }
    }
}