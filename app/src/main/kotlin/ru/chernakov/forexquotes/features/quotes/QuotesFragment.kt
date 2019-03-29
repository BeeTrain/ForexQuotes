package ru.chernakov.forexquotes.features.quotes

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_quotes.*
import ru.chernakov.forexquotes.R
import ru.chernakov.forexquotes.core.exception.Failure
import ru.chernakov.forexquotes.core.extension.*
import ru.chernakov.forexquotes.core.platform.BaseFragment
import javax.inject.Inject


class QuotesFragment : BaseFragment() {
    override val layoutRes: Int = ru.chernakov.forexquotes.R.layout.fragment_quotes

    @Inject
    lateinit var quotesAdapter: QuotesAdapter

    private lateinit var quotesViewModel: QuotesViewModel

    var isConnected: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        quotesViewModel = viewModel(viewModelFactory) {
            observe(quotes, ::renderQuotesList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadQuotes()
    }

    private fun initView() {
        quotesList.layoutManager = LinearLayoutManager(context)
        quotesList.adapter = quotesAdapter
    }

    private fun loadQuotes() {
        emptyView.invisible()
        quotesList.visible()
        quotesViewModel.initPeriodicUpdates()
    }

    private fun renderQuotesList(quotes: List<QuoteView>?) {
        if (isConnected == null || isConnected == false) {
            isConnected = true
            Snackbar.make(view!!, getString(R.string.msg_connection_restored), Snackbar.LENGTH_SHORT).show()
        }
        quotesAdapter.collection = quotes.orEmpty() as ArrayList<QuoteView>
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> {
                if (isConnected == null || isConnected == true) {
                    isConnected = false
                    Snackbar.make(view!!, getString(R.string.msg_internet_not_available), Snackbar.LENGTH_INDEFINITE)
                        .show()
                }
            }
        }
    }
}
