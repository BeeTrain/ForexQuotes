package ru.chernakov.forexquotes.features.quotes

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_quotes.*
import ru.chernakov.forexquotes.core.exception.Failure
import ru.chernakov.forexquotes.core.extension.*
import ru.chernakov.forexquotes.core.platform.BaseFragment
import javax.inject.Inject


class QuotesFragment : BaseFragment() {
    override val layoutRes: Int = ru.chernakov.forexquotes.R.layout.fragment_quotes

    @Inject
    lateinit var quotesAdapter: QuotesAdapter

    private lateinit var quotesViewModel: QuotesViewModel

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
        val onScrollListener = object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val lm = recyclerView.layoutManager as LinearLayoutManager
                    val adapter = recyclerView.adapter as QuotesAdapter

                    val first = lm.findFirstCompletelyVisibleItemPosition()
                    val last = lm.findLastCompletelyVisibleItemPosition()

                    val items = adapter.getVisibleItems(first, last)

                    val str = items.joinToString { it.symbol }

//                    quotesViewModel.loadQuotes(str)
//                    showMessage(str)
                }

            }
        }

        quotesList.addOnScrollListener(onScrollListener)
    }

    private fun loadQuotes() {
        emptyView.invisible()
        quotesList.visible()
        quotesViewModel.loadQuotes()
    }

    private fun renderQuotesList(quotes: List<QuoteView>?) {
        quotesAdapter.collection = quotes.orEmpty()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> showMessage("Ошибка интернет соединения")
        }
    }
}