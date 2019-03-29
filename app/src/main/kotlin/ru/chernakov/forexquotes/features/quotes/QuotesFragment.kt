package ru.chernakov.forexquotes.features.quotes

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat.getDrawable
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_quotes.*
import ru.chernakov.forexquotes.R
import ru.chernakov.forexquotes.core.exception.Failure
import ru.chernakov.forexquotes.core.extension.failure
import ru.chernakov.forexquotes.core.extension.observe
import ru.chernakov.forexquotes.core.extension.viewModel
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
        initView(view)
    }

    override fun onResume() {
        super.onResume()
        quotesViewModel.togglePeriodicUpdates(true)
    }

    override fun onPause() {
        super.onPause()
        quotesViewModel.togglePeriodicUpdates(false)
    }

    private fun initView(view: View) {
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.app_name)
        toolbar.navigationIcon = getDrawable(context!!.resources, R.mipmap.ic_launcher, context!!.theme)

        quotesList.layoutManager = LinearLayoutManager(context)
        quotesList.adapter = quotesAdapter
        quotesAdapter.layoutManager = quotesList.layoutManager as LinearLayoutManager
    }

    private fun renderQuotesList(quotes: List<QuoteView>?) {
        if (quotesViewModel.isConnected == null || quotesViewModel.isConnected == false) {
            quotesViewModel.isConnected = true
            Snackbar.make(view!!, getString(R.string.msg_connected), Snackbar.LENGTH_SHORT).show()
        }
        quotesAdapter.collection = quotes.orEmpty() as ArrayList<QuoteView>
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> {
                if (quotesViewModel.isConnected == null || quotesViewModel.isConnected == true) {
                    quotesViewModel.isConnected = false
                    Snackbar.make(view!!, getString(R.string.msg_internet_not_available), Snackbar.LENGTH_INDEFINITE)
                        .show()
                }
            }
        }
    }
}
