package ru.chernakov.forexquotes.features.quotes

import androidx.lifecycle.MutableLiveData
import ru.chernakov.forexquotes.core.platform.BaseViewModel
import javax.inject.Inject

class QuotesViewModel
@Inject constructor(private val getQuotes: GetQuotes) : BaseViewModel() {

    var quotes: MutableLiveData<List<QuoteView>> = MutableLiveData()

}