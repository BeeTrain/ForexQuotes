package ru.chernakov.forexquotes.features.quotes

import androidx.lifecycle.MutableLiveData
import ru.chernakov.forexquotes.core.interactor.BaseObserverSingle
import ru.chernakov.forexquotes.core.platform.BaseViewModel
import javax.inject.Inject

class QuotesViewModel
@Inject constructor(private val getQuotes: GetQuotes, private val getSymbols: GetSymbols) : BaseViewModel() {

    var quotes: MutableLiveData<List<QuoteView>> = MutableLiveData()

    fun loadQuotes() {
        getSymbols.execute(QuotesObserverSingle(), null)
    }

//    fun loadQuotes(items: String) {
//        getQuotes.execute(QuotesObserver(), items)
//    }
//
    private fun handleQuotesList(quotes: List<Quote>) {
        this.quotes.value = quotes.map { QuoteView(it.symbol, it.price, it.bid, it.ask, it.timestamp) }
    }

    private inner class QuotesObserverSingle : BaseObserverSingle<List<Quote>>() {

        override fun onSuccess(t: List<Quote>) {
            handleQuotesList(t)
        }

        override fun onError(e: Throwable) {
            super.onError(e)
        }
    }
//
//    private inner class QuotesObserver : BaseObserver<List<Quote>>() {
//        override fun onNext(t: List<Quote>) {
//            val currentList = quotes.value!!.map { toQuote(it) }
//
//            val resultList = ArrayList<Quote>()
//            for (Quote in currentList) {
//                for (QuoteT in t) {
//                    if (QuoteT.symbol != Quote.symbol) {
//                        resultList.add(Quote)
//                    } else {
//                        resultList.add(QuoteT)
//                    }
//                }
//            }
//            handleQuotesList(resultList)
//        }
//
//        override fun onComplete() {
//            super.onComplete()
//        }
//
//        override fun onError(e: Throwable) {
//            super.onError(e)
//        }
//    }
}